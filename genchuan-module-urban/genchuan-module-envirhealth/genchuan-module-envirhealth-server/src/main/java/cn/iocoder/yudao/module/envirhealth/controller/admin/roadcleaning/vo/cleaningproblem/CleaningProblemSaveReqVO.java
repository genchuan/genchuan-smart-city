package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生模块 - 道路清扫问题新增/修改 Request VO")
@Data
public class CleaningProblemSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "问题主键（UUID）", example = "323")
    private String problemId;

    @Schema(description = "关联road_cleaning.cleaning_id", example = "31676")
    private String planId;

    @Schema(description = "关联sys_problem_type.id", example = "29261")
    private String problemTypeId;

    @Schema(description = "问题位置")
    private String location;

    @Schema(description = "关联sys_user.id")
    private String reportBy;

    @Schema(description = "上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "问题描述")
    private String problemDesc;

    @Schema(description = "关联sys_team.id", example = "10062")
    private String teamId;

    @Schema(description = "处置状态：待处置/处理中/已办结", example = "2")
    private String handleStatus;

    @Schema(description = "超时提醒：是/否")
    private String isTimeout;

    @Schema(description = "处置结果")
    private String handleResult;

}