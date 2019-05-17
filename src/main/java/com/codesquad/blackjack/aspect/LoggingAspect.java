package com.codesquad.blackjack.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.codesquad.blackjack.controller..*) || within(com.codesquad.blackjack.service..*)")
    public void loggingPointcut() {}

    @Before("loggingPointcut()")
    public void printLogging() {
        log.debug("[공통 로그 - 서비스, 컨트롤러 메소드 수행 전 동작");
    }

    //TODO 좀더 유용하게 쓸수없을까?
    @Around("loggingPointcut()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        final Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());

        final String methodName = pjp.getSignature().getName();
        if (!isUtilMethod(methodName)) {
            log.debug("{}(): {}", methodName, pjp.getArgs());
        }

        Object result = pjp.proceed();

        if (!isUtilMethod(methodName)) {
            log.debug("{}(): result={}", methodName, result);
        }
        return result;
    }

    private boolean isUtilMethod(String name) {
        return name.startsWith("get") || name.startsWith("set") || name.equals("toString") || name.equals("equals")
                || name.equals("hashCode");
    }

}
