package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 转运作业 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TransferOperationRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10202")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "作业主键（UUID）", example = "21564")
    @ExcelProperty("作业主键（UUID）")
    private String operationId;

    @Schema(description = "关联sys_vehicle.id", example = "18000")
    @ExcelProperty("关联sys_vehicle.id")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "25851")
    @ExcelProperty("关联sys_garbage_type.id")
    private String garbageTypeId;

    @Schema(description = "进站时间")
    @ExcelProperty("进站时间")
    private LocalDateTime entryTime;

    @Schema(description = "垃圾重量（单位：吨）")
    @ExcelProperty("垃圾重量（单位：吨）")
    private BigDecimal garbageWeight;

    @Schema(description = "关联garbage_collection.collection_id", example = "25444")
    @ExcelProperty("关联garbage_collection.collection_id")
    private String planId;

    @Schema(description = "核心设备状态，JSON", example = "1")
    @ExcelProperty("核心设备状态，JSON")
    private String equipmentStatus;

    @Schema(description = "作业进度")
    @ExcelProperty("作业进度")
    private String progress;

    @Schema(description = "转运去向")
    @ExcelProperty("转运去向")
    private String destination;

    @Schema(description = "异常标记：是/否")
    @ExcelProperty("异常标记：是/否")
    private String abnormalIsAbnormal;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}