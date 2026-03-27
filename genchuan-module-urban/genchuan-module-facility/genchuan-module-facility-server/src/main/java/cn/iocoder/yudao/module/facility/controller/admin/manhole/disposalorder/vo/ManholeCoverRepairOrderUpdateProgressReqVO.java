package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "窨井盖维修工单进度更新请求参数")
public class ManholeCoverRepairOrderUpdateProgressReqVO {

    @NotNull(message = "工单状态不能为空")
    @Schema(description = "工单状态 1-已派单，2-维修中，3-已完成，4-已驳回，5-已取消", required = true)
    private Integer orderStatus;

    @Schema(description = "维修人ID(状态1/2必填)")
    private String repairUserId;

    @NotBlank(message = "进度备注不能为空")
    @Schema(description = "进度备注", required = true)
    private String processRemark;

    @Schema(description = "进度附件URL数组")
    private List<String> processAttachment;

    @Schema(description = "维修完成时间(状态3必填)")
    private String repairEndTime;

    @Schema(description = "实际维修内容(状态3必填)")
    private String actualRepairContent;

    @NotBlank(message = "租户ID不能为空")
    @Schema(description = "租户ID", required = true)
    private String tenantId;

    @NotBlank(message = "操作人ID不能为空")
    @Schema(description = "操作人ID", required = true)
    private String operateUserId;
}
