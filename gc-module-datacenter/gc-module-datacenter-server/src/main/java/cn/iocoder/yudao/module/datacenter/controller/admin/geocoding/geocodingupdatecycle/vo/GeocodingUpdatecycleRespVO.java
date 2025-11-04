package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingupdatecycle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 数据更新周期配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingUpdatecycleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "更新周期ID")
    @ExcelProperty("更新周期ID")
    private String updateCycleId;

    @Schema(description = "更新对象类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新对象类型")
    private String updateObjectType;

    @Schema(description = "常规更新周期")
    @ExcelProperty("常规更新周期")
    private String regularCycle;

    @Schema(description = "特殊更新触发条件")
    @ExcelProperty("特殊更新触发条件")
    private String specialTriggerCondition;

    @Schema(description = "更新责任部门")
    @ExcelProperty("更新责任部门")
    private String updateDept;

    @Schema(description = "更新流程说明")
    @ExcelProperty("更新流程说明")
    private String updateProcessDesc;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    @ExcelProperty("启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    @ExcelProperty("配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @ExcelProperty("配置时间")
    private LocalDateTime configTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    @ExcelProperty("分类扩展字段3")
    private String extCategory3;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}