package cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 户表关联及变更管理新增/修改 Request VO")
@Data
public class MeterUserRelationSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "户表编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "户表编号不能为空")
    private String meterCode;

    @Schema(description = "原用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "原用户编号不能为空")
    private String oldUserCode;

    @Schema(description = "新用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "新用户编号不能为空")
    private String newUserCode;

    @Schema(description = "变更原因")
    private String changeReason;

    @Schema(description = "变更时间")
    private LocalDateTime changeTime;

    @Schema(description = "经办人")
    private String operator;

}