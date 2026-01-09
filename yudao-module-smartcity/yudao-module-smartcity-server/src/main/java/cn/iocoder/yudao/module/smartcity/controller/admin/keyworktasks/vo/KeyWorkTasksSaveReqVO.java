package cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 重点工作任务新增/修改 Request VO")
@Data
public class KeyWorkTasksSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "241")
    private Long id;

    @Schema(description = "任务编号")
    private String taskNumber;

    @Schema(description = "任务名称")
    private String task;

    @Schema(description = "任务描述", example = "你猜")
    private String taskDescription;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时")
    private String atTheEnd;

    @Schema(description = "考核指标")
    private String assessmentIndicators;

    @Schema(description = "风险评估")
    private String riskAssessment;

    @Schema(description = "备注")
    private String notes;

}