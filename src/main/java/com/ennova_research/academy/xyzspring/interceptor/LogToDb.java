package com.ennova_research.academy.xyzspring.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alberto Ielpo
 */
@Target({ ElementType.METHOD })
@Retention( RetentionPolicy.RUNTIME )
public @interface LogToDb {

}
