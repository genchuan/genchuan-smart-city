package cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 故障诊断和预警新增/修改 Request VO")
@Data
public class DiagnosisWarningSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13049")
    private Long id;

    @Schema(description = "故障设备名称")
    private String nameFaultyEquipment;

    @Schema(description = "故障设备编号")
    private String faultEquipmentNumber;

    @Schema(description = "故障发生时间")
    private String timeFailure;

    @Schema(description = "故障现象描述")
    private String descriptionSymptoms;

    @Schema(description = "故障可能原因分析")
    private String analysisPossibleMalfunction;

    @Schema(description = "诊断方法与依据")
    private String diagnosticMethodsBasis;

    @Schema(description = "预警级别")
    private String warningLevel;

    @Schema(description = "预警时间")
    private String warningTime;

    @Schema(description = "预警信息内容")
    private String warningInformationContent;

    @Schema(description = "专家评估意见")
    private String expertEvaluationOpinions;

    @Schema(description = "系统恢复时间")
    private String systemRecoveryTime;

    @Schema(description = "故障对业务的影响程度")
    private String theDegreeBusiness;

    @Schema(description = "后续预防措施")
    private String subsequentPreventiveMeasures;

    @Schema(description = "备用设备投入情况", example = "1")
    private String backupDeploymentStatus;

}