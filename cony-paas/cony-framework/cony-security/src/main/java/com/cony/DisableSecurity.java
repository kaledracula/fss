package com.cony;

import com.cony.context.utils.IgnoreDuringScan;
import com.cony.web.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/12/2.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface DisableSecurity {

    @AliasFor(
            annotation = ComponentScan.class,
            attribute = "excludeFilters"
    )
    ComponentScan.Filter[] excludeFilters() default {@ComponentScan.Filter(IgnoreDuringScan.class)};
}
