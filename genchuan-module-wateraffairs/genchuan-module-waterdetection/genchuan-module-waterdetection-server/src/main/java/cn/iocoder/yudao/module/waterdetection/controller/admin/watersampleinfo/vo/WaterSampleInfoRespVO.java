package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 水质检测信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterSampleInfoRespVO {

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

}