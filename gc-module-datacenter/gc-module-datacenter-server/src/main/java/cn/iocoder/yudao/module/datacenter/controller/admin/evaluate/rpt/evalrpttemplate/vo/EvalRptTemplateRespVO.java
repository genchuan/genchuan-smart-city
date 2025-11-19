package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 报告模板配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalRptTemplateRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "模板ID")
    @ExcelProperty("模板ID")
    private String rptTemplateId;

    @Schema(description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模板名称")
    private String rptTemplateName;

    @Schema(description = "模板编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模板编码")
    private String rptTemplateCode;

    @Schema(description = "适用任务类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("适用任务类型")
    private String applyTaskType;

    @Schema(description = "模板文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模板文件路径")
    private String templateFilePath;

    @Schema(description = "报告结构", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报告结构")
    private String rptStructure;

    @Schema(description = "动态字段映射", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("动态字段映射")
    private String dynamicFieldMap;

    @Schema(description = "固定内容")
    @ExcelProperty("固定内容")
    private String fixedContent;

    @Schema(description = "模板版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模板版本")
    private String templateVersion;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    @ExcelProperty("更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    @ExcelProperty("更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}