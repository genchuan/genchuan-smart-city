package cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 校验生效resp VO")
@Data
public class VerifyOrderFreeRespVO {
    boolean isVerify;
    String verifyReason;
}
