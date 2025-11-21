package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报告归档导出新增/修改 Request VO")
@Data
public class EvalRptArchSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "归档ID")
    private String rptArchId;

    @Schema(description = "归档编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档编号不能为空")
    private String rptArchCode;

    @Schema(description = "关联报告ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联报告ID不能为空")
    private String evalRptId;

    @Schema(description = "报告名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报告名称不能为空")
    private String evalRptName;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "归档位置", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档位置不能为空")
    private String archLocation;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "归档时间不能为空")
    private LocalDateTime archTime;

    @Schema(description = "归档方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档方式不能为空")
    private String archMethod;

    @Schema(description = "报告版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报告版本不能为空")
    private String rptVersion;

    @Schema(description = "归档状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "归档状态不能为空")
    private String archStatus;

    @Schema(description = "失败原因")
    private String failReason;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}