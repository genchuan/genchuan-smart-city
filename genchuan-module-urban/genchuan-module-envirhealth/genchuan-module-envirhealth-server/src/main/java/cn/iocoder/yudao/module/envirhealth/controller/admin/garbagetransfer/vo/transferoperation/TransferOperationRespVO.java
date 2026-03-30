package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 转运作业 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TransferOperationRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10202")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "作业主键（UUID）", example = "21564")
    @ExcelProperty("作业主键")
    private String operationId;

    @Schema(description = "关联sys_vehicle.id", example = "18000")
    @ExcelProperty("车辆编号")
    private String vehicleId;

    @Schema(description = "关联sys_garbage_type.id", example = "25851")
    @ExcelProperty("垃圾类型编号")
    private String garbageTypeId;

    @Schema(description = "进站时间")
    @ExcelProperty("进站时间")
    private LocalDateTime entryTime;

    @Schema(description = "垃圾重量（单位：吨）")
    @ExcelProperty("垃圾重量")
    private BigDecimal garbageWeight;

    @Schema(description = "关联garbage_collection.collection_id", example = "25444")
    @ExcelProperty("计划编号")
    private String planId;

    @Schema(description = "核心设备状态，JSON", example = "1")
    @ExcelProperty("核心设备状态")
    private String equipmentStatus;

    @Schema(description = "作业进度")
    @ExcelProperty("作业进度")
    private String progress;

    @Schema(description = "转运去向")
    @ExcelProperty("转运去向")
    private String destination;

    @Schema(description = "异常标记：是/否")
    @ExcelProperty("异常标记")
    private String abnormalIsAbnormal;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}