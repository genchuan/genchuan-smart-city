package cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Schema(description = "巡查巡检 - 巡检上报批量审核 Request VO")
@Data
public class InspectReportBatchAuditReqVO {

    @Schema(description = "上报ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上报ID数组不能为空")
    private List<Long> ids;

    @Schema(description = "审核结果(字典值:2-待处置,3-已完成)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "审核结果不能为空")
    @Pattern(regexp = "^[23]$", message = "审核结果必须是2(待处置)或3(已完成)")
    private String auditResult;

    @Schema(description = "审核备注")
    private String auditRemark;
}