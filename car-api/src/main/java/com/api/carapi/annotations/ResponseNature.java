
package com.api.carapi.annotations;

import java.lang.annotation.*;

/**
 * @author thinker
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseNature {
	String value() default "";
}
