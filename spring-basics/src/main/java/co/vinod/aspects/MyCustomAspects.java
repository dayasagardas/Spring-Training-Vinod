package co.vinod.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import co.vinod.dao.DaoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class MyCustomAspects {
	public MyCustomAspects() {
		log.info("MyCustomAspects instantiated");
	}

	@AfterThrowing(pointcut = "execution (* co.vinod.dao.*Dao.*(..))", throwing = "ex")
	public void exceptionConverter(Exception ex) throws DaoException {
		throw new DaoException(ex);
	}
	
	
	// ProceedingJoinPoint can only be used in a Around advice
	@Around("execution (* co.vinod..*Dao.*(Double, Double))")
	public Object swapArgs(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Double min = (Double) args[0];
		Double max = (Double) args[1];
		if (min > max) {
			args = new Object[] { max, min };
		}
		
		return pjp.proceed(args);
	}
}





