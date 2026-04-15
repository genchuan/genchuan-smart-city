package cn.iocoder.yudao.module.kitchen.vrv.utils.procom.aop.sysope;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysOpeLog {

    /**
     * 操作类型：新增/删除/审核等
     */
    String operType() default "";

    /**
     * 操作对象：企业信息/整改记录等
     */
    String operObject() default "";

    /**
     * 操作描述（可选）
     */
    String operDesc() default "";

    /**
     * 批量操作选择条目（可选）
     */
    String batchSelectInfo() default "";
}
