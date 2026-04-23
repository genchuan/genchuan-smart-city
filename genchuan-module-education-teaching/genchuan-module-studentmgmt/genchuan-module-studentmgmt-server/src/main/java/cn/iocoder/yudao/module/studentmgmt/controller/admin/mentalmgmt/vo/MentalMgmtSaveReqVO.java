package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 心理管理新增/修改 Request VO")
@Data
public class MentalMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29416")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9996")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "心理状态：正常/关注/高危", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "心理状态：正常/关注/高危不能为空")
    private String mentalStatus;

    @Schema(description = "风险等级：低/中/高", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险等级：低/中/高不能为空")
    private String riskLevel;

    @Schema(description = "评估时间")
    private LocalDateTime evaluateTime;

    @Schema(description = "咨询预约时间")
    private LocalDateTime consultTime;

    @Schema(description = "干预时间")
    private LocalDateTime interveneTime;

    @Schema(description = "干预内容")
    private String interveneContent;

    @Schema(description = "状态：待评估/咨询中/已干预", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待评估/咨询中/已干预不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}