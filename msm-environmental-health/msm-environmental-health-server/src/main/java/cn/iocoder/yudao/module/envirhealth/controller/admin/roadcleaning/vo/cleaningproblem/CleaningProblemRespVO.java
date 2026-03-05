package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 道路清扫问题 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CleaningProblemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15877")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "问题主键（UUID）", example = "323")
    @ExcelProperty("问题主键（UUID）")
    private String problemId;

    @Schema(description = "关联road_cleaning.cleaning_id", example = "31676")
    @ExcelProperty("关联road_cleaning.cleaning_id")
    private String planId;

    @Schema(description = "关联sys_problem_type.id", example = "29261")
    @ExcelProperty("关联sys_problem_type.id")
    private String problemTypeId;

    @Schema(description = "问题位置")
    @ExcelProperty("问题位置")
    private String location;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String desc;

    @Schema(description = "关联sys_team.id", example = "10062")
    @ExcelProperty("关联sys_team.id")
    private String teamId;

    @Schema(description = "处置状态：待处置/处理中/已办结", example = "2")
    @ExcelProperty("处置状态：待处置/处理中/已办结")
    private String handleStatus;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒：是/否")
    private String isTimeout;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

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