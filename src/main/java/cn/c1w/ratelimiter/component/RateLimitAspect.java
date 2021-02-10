package cn.c1w.ratelimiter.component;

import cn.c1w.ratelimiter.annotation.RateLimit;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope
@Aspect
public class RateLimitAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private RateLimiter rateLimiter = RateLimiter.create(10);

    @Pointcut("@annotation(cn.c1w.ratelimiter.annotation.RateLimit)")
    public void ServiceAspect() { }

    @Around("ServiceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        try {
            if (rateLimiter.tryAcquire()) {
                obj = joinPoint.proceed();
            } else {
                log.info("The system is busy, please visit after a while");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}
