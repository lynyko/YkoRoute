package com.yko.route;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yko on 2017/9/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface YkoRoute {
    String path();
}
