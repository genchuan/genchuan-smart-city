package cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 巡查任务新增/修改 Request VO")
@Data
public class InspectionTaskASaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "217")
    private Long id;

    @Schema(description = "任务编号")
    private String taskNumber;

    @Schema(description = "任务名称")
    private String task;

    @Schema(description = "任务描述", example = "你说的对")
    private String taskDescription;

    @Schema(description = "巡查地点开始时间")
    private String startingTimeA;

    @Schema(description = "结束时间优先级")
    private String endTimeB;

    @Schema(description = "检查项目")
    private String inspectionItems;

    @Schema(description = "备注")
    private String notes;

}