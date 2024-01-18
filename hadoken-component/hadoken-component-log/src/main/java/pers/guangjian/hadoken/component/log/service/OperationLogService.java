package pers.guangjian.hadoken.component.log.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import pers.guangjian.hadoken.component.log.domain.po.OperationLog;
import pers.guangjian.hadoken.component.log.domain.query.OperationLogQueryCriteria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/12 23:13
 */
public interface OperationLogService {
    /**
     * 分页查询
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(OperationLogQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 查询条件
     * @return /
     */
    List<OperationLog> queryAll(OperationLogQueryCriteria criteria);

    /**
     * 查询用户日志
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return -
     */
    Object queryAllByUser(OperationLogQueryCriteria criteria, Pageable pageable);

    /**
     * 保存日志数据
     * @param username 用户
     * @param browser 浏览器
     * @param ip 请求IP
     * @param joinPoint /
     * @param log 日志实体
     */
    @Async
    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, OperationLog log);

    /**
     * 查询异常详情
     * @param id 日志ID
     * @return Object
     */
    Object findByErrDetail(Long id);

    /**
     * 导出日志
     * @param logs 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<OperationLog> logs, HttpServletResponse response) throws IOException;

    /**
     * 删除所有错误日志
     */
    void delAllByError();

    /**
     * 删除所有INFO日志
     */
    void delAllByInfo();

}
