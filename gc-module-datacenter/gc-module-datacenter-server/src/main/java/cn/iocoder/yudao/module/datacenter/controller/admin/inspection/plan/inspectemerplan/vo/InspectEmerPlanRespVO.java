package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 应急巡查计划信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectEmerPlanRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "计划ID")
    @ExcelProperty("计划ID")
    private String emerPlanId;

    @Schema(description = "计划名称")
    @ExcelProperty("计划名称")
    private String emerPlanName;

    @Schema(description = "计划编码")
    @ExcelProperty("计划编码")
    private String emerPlanCode;

    @Schema(description = "应急主题")
    @ExcelProperty("应急主题")
    private String emerTopic;

    @Schema(description = "关联应急事件ID")
    @ExcelProperty("关联应急事件ID")
    private String emerEvtId;

    @Schema(description = "关联应急事件名称")
    @ExcelProperty("关联应急事件名称")
    private String emerEvtName;

    @Schema(description = "所属区域代码")
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "关联标准ID")
    @ExcelProperty("关联标准ID")
    private String stdId;

    @Schema(description = "关联标准名称")
    @ExcelProperty("关联标准名称")
    private String stdName;

    @Schema(description = "负责人员IDs")
    @ExcelProperty("负责人员IDs")
    private String personIds;

    @Schema(description = "负责人员Names")
    @ExcelProperty("负责人员Names")
    private String personNames;

    @Schema(description = "执行时间")
    @ExcelProperty("执行时间")
    private LocalDateTime executeTime;

    @Schema(description = "执行截止时间")
    @ExcelProperty("执行截止时间")
    private LocalDateTime executeDeadline;

    @Schema(description = "计划状态")
    @ExcelProperty("计划状态")
    private Boolean planStatus;

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