package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 报告归档导出 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalRptArchRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "归档ID")
    @ExcelProperty("归档ID")
    private String rptArchId;

    @Schema(description = "归档编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档编号")
    private String rptArchCode;

    @Schema(description = "关联报告ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联报告ID")
    private String evalRptId;

    @Schema(description = "报告名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报告名称")
    private String evalRptName;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "归档位置", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档位置")
    private String archLocation;

    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档时间")
    private LocalDateTime archTime;

    @Schema(description = "归档方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档方式")
    private String archMethod;

    @Schema(description = "报告版本", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报告版本")
    private String rptVersion;

    @Schema(description = "归档状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("归档状态")
    private String archStatus;

    @Schema(description = "失败原因")
    @ExcelProperty("失败原因")
    private String failReason;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

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