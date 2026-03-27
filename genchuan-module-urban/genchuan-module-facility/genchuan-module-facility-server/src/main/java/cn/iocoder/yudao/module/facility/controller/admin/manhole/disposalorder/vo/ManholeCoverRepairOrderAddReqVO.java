package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "窨井盖维修工单新增 Request VO")
public class ManholeCoverRepairOrderAddReqVO {

    @NotBlank(message = "关联窨井盖ID不能为空")
    @Schema(description = "关联窨井盖ID", required = true)
    private String coverId;

    @Schema(description = "关联预警ID（异常维修必填）")
    private String warnId;

    @NotNull(message = "工单类型不能为空")
    @Schema(description = "工单类型 0-日常检修 1-异常维修 2-应急抢修", required = true)
    private Integer orderType;

    @NotBlank(message = "问题描述不能为空")
    @Schema(description = "问题描述", required = true)
    private String problemDesc;

    @Schema(description = "预计维修时间")
    private String expectRepairTime;

    @Schema(description = "派单人ID")
    private String assignUserId;

    @Schema(description = "维修人ID")
    private String repairUserId;

    @NotBlank(message = "联系电话不能为空")
    @Schema(description = "联系电话", required = true)
    private String contactPhone;

    @NotBlank(message = "详细地址不能为空")
    @Schema(description = "井盖详细地址", required = true)
    private String address;

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", required = true)
    private String tenantId;

    @NotBlank(message = "操作人ID不能为空")
    @Schema(description = "操作人ID", required = true)
    private String operateUserId;
}
