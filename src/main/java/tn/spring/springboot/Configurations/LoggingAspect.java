package tn.spring.springboot.Configurations;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j

public class LoggingAspect {
    private static final Logger logger =
             LogManager.getLogger(LoggingAspect.class);

    @After("execution(*ajouter* tn.spring.springboot.Controllers.*.*(..))")

    public void logMethodEntry(JoinPoint joinPoint) {

        String name = joinPoint.getSignature().getName();
        //logger.de("In method " + name + " : ***********************");
        log.info("gestion match : aAjout avec sucées  " + name + " : ");

     }
}