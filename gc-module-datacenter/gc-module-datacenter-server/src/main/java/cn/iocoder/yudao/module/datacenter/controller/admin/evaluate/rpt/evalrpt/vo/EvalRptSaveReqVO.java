package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价报告新增/修改 Request VO")
@Data
public class EvalRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报告ID")
    private String evalRptId;

    @Schema(description = "报告名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报告名称不能为空")
    private String evalRptName;

    @Schema(description = "报告编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报告编码不能为空")
    private String evalRptCode;

    @Schema(description = "关联模板ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联模板ID不能为空")
    private String rptTemplateId;

    @Schema(description = "关联模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联模板名称不能为空")
    private String rptTemplateName;

    @Schema(description = "关联存档ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联存档ID不能为空")
    private String resultArchId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "报告文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报告文件路径不能为空")
    private String rptFilePath;

    @Schema(description = "报告状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报告状态不能为空")
    private String rptStatus;

    @Schema(description = "生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "补充内容")
    private String suppleContent;

    @Schema(description = "生成人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "生成人不能为空")
    private String generateUser;

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