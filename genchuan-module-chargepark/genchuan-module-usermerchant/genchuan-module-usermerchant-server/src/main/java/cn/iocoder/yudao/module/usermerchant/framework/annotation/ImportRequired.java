package cn.iocoder.yudao.module.usermerchant.framework.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)          // 只能标记在字段上
@Retention(RetentionPolicy.RUNTIME) // 运行时保留，供反射使用
@Documented
public @interface ImportRequired {
    String message() default "该字段不能为空"; // 可自定义错误消息，后续可扩展
}