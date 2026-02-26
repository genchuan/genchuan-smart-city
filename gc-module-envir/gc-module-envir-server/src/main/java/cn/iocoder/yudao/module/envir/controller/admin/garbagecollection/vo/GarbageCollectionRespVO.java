package cn.iocoder.yudao.module.envir.controller.admin.garbagecollection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 收运计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GarbageCollectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16837")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "8139")
    @ExcelProperty("业务主键（UUID）")
    private String garbageCollectionId;

    @Schema(description = "收运计划单编号")
    @ExcelProperty("收运计划单编号")
    private String planNo;

    @Schema(description = "收运品类（关联sys_garbage_type.id）", example = "14268")
    @ExcelProperty("收运品类（关联sys_garbage_type.id）")
    private String garbageTypeId;

    @Schema(description = "收运区域（关联sys_area.area_code）")
    @ExcelProperty("收运区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "收运点位ID，多个用逗号分隔")
    @ExcelProperty("收运点位ID，多个用逗号分隔")
    private String pointIds;

    @Schema(description = "收运频次")
    @ExcelProperty("收运频次")
    private String frequency;

    @Schema(description = "收运时段")
    @ExcelProperty("收运时段")
    private String timePeriod;

    @Schema(description = "负责车辆（关联sys_vehicle.sys_vehicle_id）", example = "24748")
    @ExcelProperty("负责车辆（关联sys_vehicle.sys_vehicle_id）")
    private String vehicleId;

    @Schema(description = "负责人员，多个用逗号分隔")
    @ExcelProperty("负责人员，多个用逗号分隔")
    private String staffIds;

    @Schema(description = "计划状态（关联sys_plan_status.sys_plan_status_id）", example = "20227")
    @ExcelProperty("计划状态（关联sys_plan_status.sys_plan_status_id）")
    private String planStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    @ExcelProperty("业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "完成率")
    @ExcelProperty("完成率")
    private BigDecimal completionRate;

    @Schema(description = "异常记录数", example = "23735")
    @ExcelProperty("异常记录数")
    private Integer abnormalCount;

    @Schema(description = "异常处置明细ID，多个用逗号分隔")
    @ExcelProperty("异常处置明细ID，多个用逗号分隔")
    private String abnormalDetailIds;

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