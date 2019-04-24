package com.cn.tianxia.admin.annotation;

import java.lang.annotation.*;

/**
 * 
 * @ClassName DataSource
 * @Description 多数据源注解
 * @author Hardy
 * @Date 2019年4月16日 下午4:14:43
 * @version 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
