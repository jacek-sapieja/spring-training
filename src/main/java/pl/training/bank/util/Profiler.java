package pl.training.bank.util;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Profiler {

    private static final Logger LOGGER = Logger.getLogger(Profiler.class.getName());

    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        long totalTime = System.nanoTime() - start;
        LOGGER.log(Level.INFO, joinPoint.getSignature() + " executed in " + totalTime + " ns");
        return proceed;
    }


}
