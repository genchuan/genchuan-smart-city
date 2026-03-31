package cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.codeutils;

import java.lang.annotation.*;

/**
 * 标记“业务编号字段”
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CodeField {
}
