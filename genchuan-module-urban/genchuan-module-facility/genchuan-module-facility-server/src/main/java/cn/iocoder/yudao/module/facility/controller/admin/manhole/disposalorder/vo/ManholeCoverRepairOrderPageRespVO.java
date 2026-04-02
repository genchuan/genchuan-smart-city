package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "窨井盖维修工单分页查询 Response VO")
@Data
public class ManholeCoverRepairOrderPageRespVO {

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderId;

    @Schema(description = "工单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderNo;

    @Schema(description = "窨井盖ID")
    private String coverId;

    @Schema(description = "窨井盖编码")
    private String coverCode;

    @Schema(description = "窨井盖名称")
    private String coverName;

    @Schema(description = "预警ID")
    private String warnId;

    @Schema(description = "工单类型")
    private Integer orderType;

    @Schema(description = "工单类型名称")
    private String orderTypeName;

    @Schema(description = "问题描述")
    private String problemDesc;

    @Schema(description = "工单状态")
    private Integer orderStatus;

    @Schema(description = "工单状态名称")
    private String orderStatusName;

    @Schema(description = "派单信息")
    private AssignInfo assignInfo;

    @Schema(description = "维修信息")
    private RepairInfo repairInfo;

    @Schema(description = "最新进度备注")
    private String processRemark;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "区块链存证哈希")
    private String chainHash;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Data
    public static class AssignInfo {
        private String assignUserId;
        private String assignUserName;
        private LocalDateTime assignTime;
    }

    @Data
    public static class RepairInfo {
        private String repairUserId;
        private String repairUserName;
        private LocalDateTime repairStartTime;
        private LocalDateTime repairEndTime;
    }
}
