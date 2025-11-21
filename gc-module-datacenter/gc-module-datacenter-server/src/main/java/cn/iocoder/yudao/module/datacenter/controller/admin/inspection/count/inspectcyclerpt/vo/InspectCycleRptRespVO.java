package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectcyclerpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检周期统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectCycleRptRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID")
    @ExcelProperty("统计ID")
    private String cycleStatId;

    @Schema(description = "统计维度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计维度")
    private String statDimension;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属区域名称")
    private String areaName;

    @Schema(description = "周期列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("周期列表")
    private String cycleList;

    @Schema(description = "巡查次数列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡查次数列表")
    private String inspectCountList;

    @Schema(description = "问题数列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题数列表")
    private String problemCountList;

    @Schema(description = "整改率列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("整改率列表")
    private String rectifyRateList;

    @Schema(description = "平均问题发现率列表")
    @ExcelProperty("平均问题发现率列表")
    private String avgFoundRateList;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUser;

    @Schema(description = "统计时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间(业务)")
    private LocalDateTime statTimeBiz;

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