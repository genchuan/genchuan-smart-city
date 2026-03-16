package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 道路清扫问题 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CleaningProblemRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "问题主键（UUID）", example = "323")
    @ExcelProperty("问题主键")
    private String problemId;

    @Schema(description = "关联road_cleaning.cleaning_id", example = "31676")
    @ExcelProperty("计划编号")
    private String planId;

    @Schema(description = "关联sys_problem_type.id", example = "29261")
    @ExcelProperty("问题类型编号")
    private String problemTypeId;

    @Schema(description = "问题位置")
    @ExcelProperty("问题位置")
    private String location;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("上报人员")
    private String reportBy;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "问题描述")
    @ExcelProperty("问题描述")
    private String problemDesc;

    @Schema(description = "关联sys_team.id", example = "10062")
    @ExcelProperty("班组编号")
    private String teamId;

    @Schema(description = "处置状态：待处置/处理中/已办结", example = "2")
    @ExcelProperty("处置状态")
    private String handleStatus;

    @Schema(description = "超时提醒：是/否")
    @ExcelProperty("超时提醒")
    private String isTimeout;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}