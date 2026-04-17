package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预约列表新增/修改 Request VO")
@Data
public class ReserveListSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "场站 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "场站 ID 不能为空")
    private Long stationId;

    @Schema(description = "车位 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
    @NotNull(message = "车位 ID 不能为空")
    private Long spaceId;

    @Schema(description = "预约时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预约时间不能为空")
    private LocalDateTime reserveTime;

    @Schema(description = "预约类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "停车预约")
    @NotBlank(message = "预约类型不能为空")
    private String reserveType;

    @Schema(description = "预约状态（创建时由后端默认为 待审核）", example = "待审核")
    private String status;

    @Schema(description = "审核人 ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "评价得分，1-5 分", example = "5")
    @Min(value = 1, message = "评分最小为 1")
    @Max(value = 5, message = "评分最大为 5")
    private Integer score;

    @Schema(description = "审核备注")
    private String auditRemark;

    @Schema(description = "驳回理由")
    private String rejectReason;

    @Schema(description = "评价内容")
    private String evaluateContent;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
