package cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 在线数据与实验室比对 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OnlineLabComparisonRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "比对日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("比对日期")
    private LocalDateTime comparisonDate;

    @Schema(description = "监测点ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测点ID")
    private String monitorPointId;

    @Schema(description = "仪器类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("仪器类型")
    private String instrumentType;

    @Schema(description = "在线监测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("在线监测值")
    private Double onlineValue;

    @Schema(description = "实验室检测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("实验室检测值")
    private Double labValue;

    @Schema(description = "偏差值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("偏差值")
    private Double deviationValue;

    @Schema(description = "是否超标(0否1是)")
    @ExcelProperty("是否超标(0否1是)")
    private Boolean isExceeded;

    @Schema(description = "预警状态")
    @ExcelProperty("预警状态")
    private String warningStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}