package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 结果报表管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalResultRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "报表ID")
    @ExcelProperty("报表ID")
    private String resultRptId;

    @Schema(description = "报表名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表名称")
    private String resultRptName;

    @Schema(description = "报表编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表编码")
    private String resultRptCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务ID")
    private String evalTaskId;

    @Schema(description = "关联任务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联任务名称")
    private String evalTaskName;

    @Schema(description = "报表类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表类型")
    private String rptType;

    @Schema(description = "统计维度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计维度")
    private String statDimension;

    @Schema(description = "行政区划代码")
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称")
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "报表字段", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表字段")
    private String rptFields;

    @Schema(description = "生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "报表状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表状态")
    private String rptStatus;

    @Schema(description = "文件路径")
    @ExcelProperty("文件路径")
    private String filePath;

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