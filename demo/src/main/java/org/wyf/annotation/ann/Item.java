package org.wyf.annotation.ann;

import java.lang.annotation.*;

/**
 * Created by wyf on 16/9/24.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Item {
    String value();
}
