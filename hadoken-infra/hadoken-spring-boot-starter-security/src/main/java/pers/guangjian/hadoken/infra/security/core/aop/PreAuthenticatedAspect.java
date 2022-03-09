package pers.guangjian.hadoken.infra.security.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import pers.guangjian.hadoken.infra.security.core.annotations.PreAuthenticated;
import pers.guangjian.hadoken.infra.security.core.util.SecurityUtils;

import static pers.guangjian.hadoken.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static pers.guangjian.hadoken.common.exception.util.HadokenServiceExceptionUtil.exception;

/**
 * @author yanggj
 * @date 2022/03/01 18:14
 * @version 1.0.0
 */
public class PreAuthenticatedAspect {

    @Around("@annotation(preAuthenticated)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable {
        if (SecurityUtils.getLoginUser() == null) {
            throw exception(UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }
}
