package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 户外广告 Remove Request VO")
@Data
@ToString
public class OutdoorAdRemoveReqVO {

    @Schema(description = "广告ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24013")
    @NotEmpty(message = "广告ID不能为空")
    private String outdoorAdId;


}