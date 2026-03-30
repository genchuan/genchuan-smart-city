package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 水质检测信息WaterSampleExportVO")
@Data
@ExcelIgnoreUnannotated
public class WaterSampleExportVO {

    //以下主表
    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "样品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("样品编号")
    private String sampleNo;

    @Schema(description = "样品类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("样品类型")
    private String sampleType;

    @Schema(description = "样品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("样品名称")
    private String sampleName;

    @Schema(description = "检测性质", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测性质")
    private String sampleNature;

    @Schema(description = "样品状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("样品状态")
    private String sampleStatus;

    @Schema(description = "采样方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样方式")
    private String deliveryMethod;

    @Schema(description = "采样时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样时间")
    private LocalDateTime samplingDate;

    @Schema(description = "采样地点", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样地点")
    private String samplingLocation;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "检测开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测开始日期")
    private LocalDateTime startDate;

    @Schema(description = "检测结束日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测结束日期")
    private LocalDateTime endDate;

    @Schema(description = "检测依据")
    @ExcelProperty("检测依据")
    private String standard;

    @Schema(description = "结果报告")
    @ExcelProperty("结果报告")
    private String sampleResult;

    @Schema(description = "检测结论")
    @ExcelProperty("检测结论")
    private String conclusion;

    //以下子表
    @Schema(description = "样品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "16063")
    @ExcelProperty("样品编号")
    private Long waterSampleId;

    @Schema(description = "指标", example = "芋艿")
    @ExcelProperty("指标")
    private String indexName;

    @Schema(description = "实测值")
    @ExcelProperty("实测值")
    private Double actualValue;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}