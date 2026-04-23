package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 异常离场新增/修改 Request VO")
@Data
public class AbnormalLeaveSaveReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "异常类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "异常类型不能为空")
    private String abnormalType;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "识别时间不能为空")
    private LocalDateTime identifyTime;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置状态不能为空")
    private String status;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "处置人ID")
    private Long handleUserId;

    @Schema(description = "处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置进度")
    private String handleProgress;

    @Schema(description = "忽略理由")
    private String ignoreReason;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}