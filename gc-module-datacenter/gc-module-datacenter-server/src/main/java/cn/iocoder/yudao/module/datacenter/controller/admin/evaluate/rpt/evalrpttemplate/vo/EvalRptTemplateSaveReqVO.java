package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报告模板配置新增/修改 Request VO")
@Data
public class EvalRptTemplateSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "模板ID")
    private String rptTemplateId;

    @Schema(description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模板名称不能为空")
    private String rptTemplateName;

    @Schema(description = "模板编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模板编码不能为空")
    private String rptTemplateCode;

    @Schema(description = "适用任务类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用任务类型不能为空")
    private String applyTaskType;

    @Schema(description = "模板文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模板文件路径不能为空")
    private String templateFilePath;

    @Schema(description = "报告结构", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报告结构不能为空")
    private String rptStructure;

    @Schema(description = "动态字段映射", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "动态字段映射不能为空")
    private String dynamicFieldMap;

    @Schema(description = "固定内容")
    private String fixedContent;

    @Schema(description = "模板版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模板版本不能为空")
    private String templateVersion;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}