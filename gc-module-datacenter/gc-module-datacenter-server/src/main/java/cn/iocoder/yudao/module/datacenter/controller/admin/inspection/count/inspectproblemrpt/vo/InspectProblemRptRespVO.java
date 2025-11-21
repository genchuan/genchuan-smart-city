package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检问题统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectProblemRptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计ID")
    private String problemStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "问题类型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题类型ID")
    private String problemTypeId;

    @Schema(description = "问题类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题类型名称")
    private String problemTypeName;

    @Schema(description = "上报问题数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上报问题数")
    private Integer rptProblemCount;

    @Schema(description = "一级问题数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("一级问题数")
    private Integer level1ProblemCount;

    @Schema(description = "整改完成数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改完成数")
    private Integer rectifyCompleteCount;

    @Schema(description = "整改率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改率")
    private BigDecimal rectifyRate;

    @Schema(description = "未整改原因")
    @ExcelProperty("未整改原因")
    private String unrectifiedReason;

    @Schema(description = "问题集中区域")
    @ExcelProperty("问题集中区域")
    private String concentratedArea;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUser;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

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