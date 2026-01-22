package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    private final MethodCallCounter counter;

    public LoggingAspect(MethodCallCounter counter) {
        this.counter = counter;
    }

    @Around("execution(* com.example.shop..*(..)) && (@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Service))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        long callCount = counter.increment(methodName);

        log.info("Call #{} -> Method: {}, Args: {}",
                callCount, methodName, Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed();
            log.info("RETURN -> Method: {}, Result: {}", methodName, result);
        } catch (Throwable ex) {
            log.error("ERROR -> Method: {}, Mesage: {}", methodName, ex.getMessage());
            throw ex;
        }

        return result;
    }

}
