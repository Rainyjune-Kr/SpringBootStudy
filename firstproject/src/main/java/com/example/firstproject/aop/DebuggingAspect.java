package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP Class 선언
@Component // IoC Container가 객체 생성 관리
@Slf4j
public class DebuggingAspect {
    // 대상 Method 선택 : CommentService#create()
    @Pointcut("execution(* com.example.firstproject.service.CommentService.*(..))")
    public void cut() {}

    // 실행 시점 설정
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { // cut()의 대상 method
        // 입력값
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature().getName();

        // 로깅
        for (Object obj : args) { // foreach
            log.info("Input values of {}#{} => {}", className, methodName, args);
        }
    }

    // cut() 실행 성공 후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, Object returnObj) {
        // 클래스명
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // 메소드명
        String methodName = joinPoint.getSignature().getName();

        log.info("Return values of {}#{} => {}", className, methodName, returnObj);
    }
}
