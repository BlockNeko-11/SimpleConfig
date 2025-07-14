package io.github.blockneko11.simpleconfig.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示一个字段的别名，用于解析与序列化。
 * @author BlockNeko-11
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Alias {
    /**
     * 获取字段别名。
     * @return 字段别名
     */
    String value();
}
