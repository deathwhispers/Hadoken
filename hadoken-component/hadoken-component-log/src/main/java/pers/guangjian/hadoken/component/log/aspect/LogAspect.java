package pers.guangjian.hadoken.component.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import pers.guangjian.hadoken.common.util.RequestHolder;
import pers.guangjian.hadoken.common.util.ThrowableUtil;
import pers.guangjian.hadoken.common.util.string.StringUtils;
import pers.guangjian.hadoken.component.log.domain.po.OperationLog;
import pers.guangjian.hadoken.component.log.service.OperationLogService;
import pers.guangjian.hadoken.infra.security.core.util.SecurityUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/3/13 21:43
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private final OperationLogService operationLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LogAspect(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(pers.guangjian.hadoken.component.log.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        OperationLog log = new OperationLog("INFO",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        operationLogService.save(getUsername(), StringUtils.getBrowser(request), StringUtils.getIp(request),joinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        OperationLog log = new OperationLog("ERROR",System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e).getBytes());
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        operationLogService.save(getUsername(), StringUtils.getBrowser(request), StringUtils.getIp(request), (ProceedingJoinPoint)joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getCurrentUsername();
        }catch (Exception e){
            return "";
        }
    }
}
