package cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 离场记录新增/修改 Request VO")
@Data
public class ParkCarExitSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13125")
    private Long id;

    @Schema(description = "离场记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "22619")
    @NotEmpty(message = "离场记录ID（UUID）不能为空")
    private String exitId;

    @Schema(description = "入场记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11110")
    @NotEmpty(message = "入场记录ID不能为空")
    private String entryId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String carNumber;

    @Schema(description = "离场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离场时间不能为空")
    private LocalDateTime exitTime;

    @Schema(description = "离场出入口ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20426")
    @NotEmpty(message = "离场出入口ID不能为空")
    private String exitExitId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18494")
    @NotEmpty(message = "所属车场ID不能为空")
    private String lotId;

    @Schema(description = "停放时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "停放时长（分钟）不能为空")
    private Integer parkingDuration;

    @Schema(description = "应缴费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应缴费用不能为空")
    private BigDecimal feeAmount;

    @Schema(description = "实缴费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实缴费用不能为空")
    private BigDecimal actualPayAmount;

    @Schema(description = "缴费状态：未缴费/已缴费/部分缴费", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "缴费状态：未缴费/已缴费/部分缴费不能为空")
    private String payStatus;

    @Schema(description = "缴费记录ID", example = "18990")
    private String paymentId;

    @Schema(description = "离场类型：正常/异常/特殊放行", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "离场类型：正常/异常/特殊放行不能为空")
    private String exitType;

    @Schema(description = "异常原因", example = "不香")
    private String abnormalReason;

    @Schema(description = "识别设备", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "识别设备不能为空")
    private String deviceCode;

    @Schema(description = "业务创建时间")
    private LocalDateTime exitCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime exitUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String exitRemark;

}