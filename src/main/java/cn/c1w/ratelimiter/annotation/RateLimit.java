package cn.c1w.ratelimiter.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    // the default tokens per second
    double limitNum() default 10;
}
