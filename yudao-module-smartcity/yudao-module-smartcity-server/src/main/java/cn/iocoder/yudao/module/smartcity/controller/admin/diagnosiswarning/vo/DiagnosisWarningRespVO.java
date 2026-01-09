package cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 故障诊断和预警 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DiagnosisWarningRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13049")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "故障设备名称")
    @ExcelProperty("故障设备名称")
    private String nameFaultyEquipment;

    @Schema(description = "故障设备编号")
    @ExcelProperty("故障设备编号")
    private String faultEquipmentNumber;

    @Schema(description = "故障发生时间")
    @ExcelProperty("故障发生时间")
    private String timeFailure;

    @Schema(description = "故障现象描述")
    @ExcelProperty("故障现象描述")
    private String descriptionSymptoms;

    @Schema(description = "故障可能原因分析")
    @ExcelProperty("故障可能原因分析")
    private String analysisPossibleMalfunction;

    @Schema(description = "诊断方法与依据")
    @ExcelProperty("诊断方法与依据")
    private String diagnosticMethodsBasis;

    @Schema(description = "预警级别")
    @ExcelProperty("预警级别")
    private String warningLevel;

    @Schema(description = "预警时间")
    @ExcelProperty("预警时间")
    private String warningTime;

    @Schema(description = "预警信息内容")
    @ExcelProperty("预警信息内容")
    private String warningInformationContent;

    @Schema(description = "专家评估意见")
    @ExcelProperty("专家评估意见")
    private String expertEvaluationOpinions;

    @Schema(description = "系统恢复时间")
    @ExcelProperty("系统恢复时间")
    private String systemRecoveryTime;

    @Schema(description = "故障对业务的影响程度")
    @ExcelProperty("故障对业务的影响程度")
    private String theDegreeBusiness;

    @Schema(description = "后续预防措施")
    @ExcelProperty("后续预防措施")
    private String subsequentPreventiveMeasures;

    @Schema(description = "备用设备投入情况", example = "1")
    @ExcelProperty("备用设备投入情况")
    private String backupDeploymentStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}