package co.vinod.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component // qualifies for component-scan
public class LoggerAspect {

	public LoggerAspect() {
		log.info("LoggerAspect instantiated!");
	}
	
	@Pointcut("execution (* co.vinod..*Dao.*(..))")
	public void pc1() {}
	
	@Pointcut("execution (* co.vinod.aspects.My*.*(..))")
	public void pc2() {}
	
	@Before("pc1() || pc2()")
	public void logBefore(JoinPoint jp) {
		log.info("entering the function {}.{}() with arguments {} ", 
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				jp.getArgs());
	}
	
	@After("pc1() || pc2()")
	public void logAfter(JoinPoint jp) {
		log.info("exiting the function {}.{}() with arguments {} ", 
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(),
				jp.getArgs());
	}
	
	
}
