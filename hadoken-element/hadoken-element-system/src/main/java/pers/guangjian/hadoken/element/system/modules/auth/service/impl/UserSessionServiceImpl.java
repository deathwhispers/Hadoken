package pers.guangjian.hadoken.element.system.modules.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.guangjian.hadoken.common.entity.PageResult;
import pers.guangjian.hadoken.common.util.collection.CollectionUtils;
import pers.guangjian.hadoken.element.system.modules.auth.domain.UserSessionDO;
import pers.guangjian.hadoken.element.system.modules.auth.repository.UserSessionRepository;
import pers.guangjian.hadoken.element.system.modules.auth.rest.vo.UserSessionPageReqVO;
import pers.guangjian.hadoken.element.system.modules.auth.service.UserSessionService;
import pers.guangjian.hadoken.element.system.modules.auth.service.mapstruct.UserSessionMapper;
import pers.guangjian.hadoken.element.system.modules.user.domain.User;
import pers.guangjian.hadoken.element.system.modules.user.service.UserService;
import pers.guangjian.hadoken.infra.security.core.LoginUser;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 10:11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserSessionServiceImpl implements UserSessionService {

    private final UserService userService;
    private final UserSessionMapper userSessionMapper;
    private final UserSessionRepository userSessionRepository;

    @Override
    public PageResult<UserSessionDO> getUserSessionPage(UserSessionPageReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUsername())) {
            userIds = CollectionUtils.convertSet(userService.getUsersByUsername(reqVO.getUsername()), User::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        return userSessionMapper.selectPage(reqVO, userIds);
    }

    @Override
    public long clearSessionTimeout() {
        // 获取db里已经超时的用户列表
        List<UserSessionDO> sessionTimeoutDOS = userSessionMapper.selectListBySessionTimoutLt();
        Map<String, UserSessionDO> timeoutSessionDOMap = sessionTimeoutDOS
                .stream()
                .filter(sessionDO -> loginUserRedisDAO.get(sessionDO.getId()) == null)
                .collect(Collectors.toMap(UserSessionDO::getId, o -> o));
        // 确认已经超时,按批次移出在线用户列表
        if (CollUtil.isNotEmpty(timeoutSessionDOMap)) {
            Lists.partition(new ArrayList<>(timeoutSessionDOMap.keySet()), 100)
                    .forEach(userSessionMapper::deleteBatchIds);
            // 记录用户超时退出日志
            createTimeoutLogoutLog(timeoutSessionDOMap.values());
        }
        return timeoutSessionDOMap.size();
    }

    @Override
    public String createUserSession(LoginUser loginUser) {
        // 生成 Session 编号
        String sessionId = generateSessionId();
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserRedisDAO.set(sessionId, loginUser);
        // 写入 DB 中
        UserSessionDO userSession = UserSessionDO.builder().id(sessionId)
                .userId(loginUser.getId()).userType(loginUser.getUserType())
                .userIp(userIp).userAgent(userAgent).username(loginUser.getUsername())
                .sessionTimeout(addTime(Duration.ofMillis(getSessionTimeoutMillis())))
                .build();
        userSessionMapper.insert(userSession);
        // 返回 Session 编号
        return sessionId;
    }

    @Override
    public void refreshUserSession(String sessionId, LoginUser loginUser) {
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserRedisDAO.set(sessionId, loginUser);
        // 更新 DB 中
        UserSessionDO updateObj = UserSessionDO.builder().id(sessionId).build();
        updateObj.setUsername(loginUser.getUsername());
        updateObj.setUpdateTime(new Date());
        updateObj.setSessionTimeout(addTime(Duration.ofMillis(getSessionTimeoutMillis())));
        userSessionMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserSession(String sessionId) {
        // 删除 Redis 缓存
        loginUserRedisDAO.delete(sessionId);
        // 删除 DB 记录
        userSessionMapper.deleteById(sessionId);
    }

    @Override
    public LoginUser getLoginUser(String sessionId) {
        return loginUserRedisDAO.get(sessionId);
    }

    @Override
    public Long getSessionTimeoutMillis() {
        return securityProperties.getSessionTimeout().toMillis();
    }

    /**
     * 生成 Session 编号，目前采用 UUID 算法
     *
     * @return Session 编号
     */
    private static String generateSessionId() {
        return IdUtil.fastSimpleUUID();
    }

}
