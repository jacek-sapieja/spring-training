package pl.training.bank.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
public class Profiler {

    private static final Logger LOGGER = Logger.getLogger(Profiler.class.getName());

    @Around("@annotation(Profile)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long totalTime = System.nanoTime() - start;
        LOGGER.log(Level.INFO, joinPoint.getSignature() + " executed in " + totalTime + "n s");
        return proceed;
    }


}
