package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 日常巡查计划信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectDailyPlanRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "计划ID")
    @ExcelProperty("计划ID")
    private String dailyPlanId;

    @Schema(description = "计划名称")
    @ExcelProperty("计划名称")
    private String dailyPlanName;

    @Schema(description = "计划编码")
    @ExcelProperty("计划编码")
    private String dailyPlanCode;

    @Schema(description = "所属区域代码")
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "关联路线ID")
    @ExcelProperty("关联路线ID")
    private String routeId;

    @Schema(description = "关联路线名称")
    @ExcelProperty("关联路线名称")
    private String routeName;

    @Schema(description = "关联标准ID")
    @ExcelProperty("关联标准ID")
    private String stdId;

    @Schema(description = "关联标准名称")
    @ExcelProperty("关联标准名称")
    private String stdName;

    @Schema(description = "负责人员ID")
    @ExcelProperty("负责人员ID")
    private String personId;

    @Schema(description = "负责人员姓名")
    @ExcelProperty("负责人员姓名")
    private String personName;

    @Schema(description = "计划周期")
    @ExcelProperty("计划周期")
    private String planCycle;

    @Schema(description = "执行开始时间")
    @ExcelProperty("执行开始时间")
    private LocalTime executeStartTime;

    @Schema(description = "执行结束时间")
    @ExcelProperty("执行结束时间")
    private LocalTime executeEndTime;

    @Schema(description = "计划状态")
    @ExcelProperty("计划状态")
    private Boolean planStatus;

    @Schema(description = "开始日期")
    @ExcelProperty("开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    @ExcelProperty("结束日期")
    private LocalDate endDate;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}