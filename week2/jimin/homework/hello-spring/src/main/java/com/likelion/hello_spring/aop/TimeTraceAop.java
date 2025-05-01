package com.likelion.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component  // 스프링 빈으로 등록
@Aspect     // aop를 사용하기 위한 애노테이션
public class TimeTraceAop
{
    // excute 메소드를 어디에 적용할 것인지 타겟팅 (하위 패키지 전부에 적용함)
    @Around("execution(* com.likelion.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}
