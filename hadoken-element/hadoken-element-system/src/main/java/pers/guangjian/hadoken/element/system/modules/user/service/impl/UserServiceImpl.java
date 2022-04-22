package pers.guangjian.hadoken.element.system.modules.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.guangjian.hadoken.common.entity.PageResult;
import pers.guangjian.hadoken.element.system.modules.user.domain.User;
import pers.guangjian.hadoken.element.system.modules.user.repository.UserRepository;
import pers.guangjian.hadoken.element.system.modules.user.rest.vo.*;
import pers.guangjian.hadoken.element.system.modules.user.service.UserService;
import pers.guangjian.hadoken.element.system.modules.user.service.mapstruct.UserMapper;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 9:30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Long createUser(UserCreateReqVO reqVO) {
        return null;
    }

    @Override
    public void updateUser(UserUpdateReqVO reqVO) {

    }

    @Override
    public void updateUserLogin(Long id, String loginIp) {

    }

    @Override
    public void updateUserProfile(Long id, UserProfileUpdateReqVO reqVO) {

    }

    @Override
    public void updateUserPassword(Long id, UserProfileUpdatePasswordReqVO reqVO) {

    }

    @Override
    public String updateUserAvatar(Long id, InputStream avatarFile) throws Exception {
        return null;
    }

    @Override
    public void updateUserPassword(Long id, String password) {

    }

    @Override
    public void updateUserStatus(Long id, Integer status) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public PageResult<User> getUserPage(UserPageReqVO reqVO) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public List<User> getUsersByDeptIds(Collection<Long> deptIds) {
        return null;
    }

    @Override
    public List<User> getUsersByPostIds(Collection<Long> postIds) {
        return null;
    }

    @Override
    public List<User> getUsers(Collection<Long> ids) {
        return null;
    }

    @Override
    public void validUsers(Set<Long> ids) {

    }

    @Override
    public List<User> getUsers(UserExportReqVO reqVO) {
        return null;
    }

    @Override
    public List<User> getUsersByNickname(String nickname) {
        return null;
    }

    @Override
    public List<User> getUsersByUsername(String username) {
        return null;
    }

    @Override
    public UserImportRespVO importUsers(List<UserImportExcelVO> importUsers, boolean isUpdateSupport) {
        return null;
    }

    @Override
    public List<User> getUsersByStatus(Integer status) {
        return null;
    }
}
