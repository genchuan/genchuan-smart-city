package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 无牌入场 Response VO")
@Data
public class UnplateEnterVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "车辆类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String carType;

    @Schema(description = "车辆颜色")
    private String carColor;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @Schema(description = "登记时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime registerTime;

    @Schema(description = "审核状态：待审核/已通过/已驳回", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long stationId;

    @Schema(description = "审核人ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见")
    private String auditComment;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}