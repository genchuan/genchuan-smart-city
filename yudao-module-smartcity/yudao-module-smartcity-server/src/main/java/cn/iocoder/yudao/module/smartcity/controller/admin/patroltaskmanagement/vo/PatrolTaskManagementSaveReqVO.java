package cn.iocoder.yudao.module.smartcity.controller.admin.patroltaskmanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 巡査任务管理新增/修改 Request VO")
@Data
public class PatrolTaskManagementSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22109")
    private Long id;

    @Schema(description = "任务名称")
    private String task;

    @Schema(description = "所属计划")
    private String belongingPlan;

    @Schema(description = "巡查区域")
    private String patrolArea;

    @Schema(description = "巡查时间")
    private String patrolTime;

    @Schema(description = "执行人员")
    private String executive;

    @Schema(description = "预计时长")
    private String expectedDuration;

    @Schema(description = "任务描述", example = "你猜")
    private String taskDescription;

    @Schema(description = "巡查重点")
    private String keyInspectionPoints;

    @Schema(description = "携带设备清单")
    private String listOfCarryingEquipment;

    @Schema(description = "完成情况说明", example = "你猜")
    private String completionStatusDescription;

    @Schema(description = "异常情况记录")
    private String abnormalSituationRecord;

    @Schema(description = "处理措施")
    private String handlingMeasures;

}