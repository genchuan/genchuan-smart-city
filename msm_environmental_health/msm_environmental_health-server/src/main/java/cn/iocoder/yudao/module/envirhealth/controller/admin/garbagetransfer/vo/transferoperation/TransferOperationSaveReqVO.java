package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 转运作业新增/修改 Request VO")
@Data
public class TransferOperationSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10202")
    private Long id;

    @Schema(description = "作业主键（UUID）", example = "21564")
    private String operationId;

    @Schema(description = "关联sys_vehicle.id", example = "18000")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "25851")
    private String garbageTypeId;

    @Schema(description = "进站时间")
    private LocalDateTime entryTime;

    @Schema(description = "垃圾重量（单位：吨）")
    private BigDecimal garbageWeight;

    @Schema(description = "关联garbage_collection.collection_id", example = "25444")
    private String planId;

    @Schema(description = "核心设备状态，JSON", example = "1")
    private String equipmentStatus;

    @Schema(description = "作业进度")
    private String progress;

    @Schema(description = "转运去向")
    private String destination;

    @Schema(description = "异常标记：是/否")
    private String abnormalIsAbnormal;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}