package com.cony;

import com.cony.web.EnableWeb;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.lang.annotation.*;

/**
 * Created by dongb-a on 2017/6/2.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableJpaAuditing
@EnableWeb
@Import(FileDefaultConfig.class)
public @interface EnableFile {

}
