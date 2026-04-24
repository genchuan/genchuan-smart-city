package cn.iocoder.yudao.module.stationresource.vrv.utils.common.userfill;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FillUserInfo {
    FillMode mode() default FillMode.FIXED;

    enum FillMode {
        FIXED
    }
}
