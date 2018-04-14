package com.mmall.concurrency.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * 用来标记 【推荐】 的类或者写法
 *
 */

@Retention(SOURCE)
@Target(TYPE)
public @interface Recommend {
	String value() default "";
}
