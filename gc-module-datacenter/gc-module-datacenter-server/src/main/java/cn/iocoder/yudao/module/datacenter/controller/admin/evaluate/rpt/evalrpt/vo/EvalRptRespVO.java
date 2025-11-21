package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 评价报告 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "报告ID")
    @ExcelProperty("报告ID")
    private String evalRptId;

    @Schema(description = "报告名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报告名称")
    private String evalRptName;

    @Schema(description = "报告编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报告编码")
    private String evalRptCode;

    @Schema(description = "关联模板ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联模板ID")
    private String rptTemplateId;

    @Schema(description = "关联模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联模板名称")
    private String rptTemplateName;

    @Schema(description = "关联存档ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联存档ID")
    private String resultArchId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "报告文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报告文件路径")
    private String rptFilePath;

    @Schema(description = "报告状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报告状态")
    private String rptStatus;

    @Schema(description = "生成时间")
    @ExcelProperty("生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "补充内容")
    @ExcelProperty("补充内容")
    private String suppleContent;

    @Schema(description = "生成人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生成人")
    private String generateUser;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

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