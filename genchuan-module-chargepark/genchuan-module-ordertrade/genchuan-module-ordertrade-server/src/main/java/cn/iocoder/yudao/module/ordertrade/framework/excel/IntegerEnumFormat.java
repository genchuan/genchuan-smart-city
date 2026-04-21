package cn.iocoder.yudao.module.ordertrade.framework.excel;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IntegerEnumFormat {
    /**
     * 枚举类，必须有静态方法 labelOf(Integer value)
     */
    Class<?> value();
}
