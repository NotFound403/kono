package cn.felord.kono.mybatis;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Demarcates an identifier.
 *
 * @author felord.cn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { FIELD, METHOD, ANNOTATION_TYPE })
public @interface PrimaryKey {
}
