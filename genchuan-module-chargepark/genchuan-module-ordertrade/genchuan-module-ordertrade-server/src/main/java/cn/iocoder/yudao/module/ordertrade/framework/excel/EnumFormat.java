// 路径: framework/excel/EnumFormat.java
package cn.iocoder.yudao.module.ordertrade.framework.excel;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumFormat {
    /**
     * 枚举类，必须有静态方法 labelOf(String value)
     */
    Class<?> value();
}
