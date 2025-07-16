package io.github.blockneko11.simpleconfig.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示一个配置项的注释，也可以表示配置的根注释。
 * @author BlockNeko-11
 * @since 1.0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Comment {
    /**
     * 获取注释，数组每一项为一行注释。
     * @return 注释
     */
    String[] value() default {};
}
