package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 心理管理状态更新 Request VO")
@Data
public class MentalMgmtUpdateStatusReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29416")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "心理状态：正常/关注/高危", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "心理状态：正常/关注/高危不能为空")
    private String mentalStatus;

    @Schema(description = "风险等级：低/中/高", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险等级：低/中/高不能为空")
    private String riskLevel;
}