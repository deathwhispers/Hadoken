package pers.guangjian.hadoken.infra.security.core.aop;

import pers.guangjian.hadoken.infra.security.core.util.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.security.access.prepost.PreAuthorize;

import static pers.guangjian.hadoken.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static pers.guangjian.hadoken.common.exception.util.HadokenServiceExceptionUtil.exception;

/**
 * @Author: yanggj
 * @Description: TODO
 * @Date: 2022/03/01 18:14
 * @Version: 1.0.0
 */
public class PreAuthenticatedAspect {

    @Around("@annotation(preAuthorize)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthorize preAuthenticated) throws Throwable {
        if (SecurityUtils.getLoginUser() == null) {
            throw exception(UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }
}
