package kr.co.songjava.framework.web.bind.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestConfig {

    boolean loginCheck() default false;

}
