package org.wyf.annotation.ann;

import java.lang.annotation.*;

/**
 * Created by wyf on 16/9/24.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface UserInfo {
    String value();
}
