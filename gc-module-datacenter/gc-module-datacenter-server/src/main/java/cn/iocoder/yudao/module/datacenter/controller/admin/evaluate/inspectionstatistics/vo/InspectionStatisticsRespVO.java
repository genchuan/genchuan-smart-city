package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 环卫考核统计结果 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionStatisticsRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "考核日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考核日期")
    private String inspectionDate;

    @Schema(description = "场所类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("场所类型")
    private String areaType;

    @Schema(description = "区域/地点名称")
    @ExcelProperty("区域/地点名称")
    private String areaName;

    @Schema(description = "该场所总得分")
    @ExcelProperty("该场所总得分")
    private BigDecimal totalScore;

    @Schema(description = "该类型满分")
    @ExcelProperty("该类型满分")
    private BigDecimal maxScore;

    @Schema(description = "权重")
    @ExcelProperty("权重")
    private BigDecimal weight;

    @Schema(description = "最终得分")
    @ExcelProperty("最终得分")
    private BigDecimal scoreWeighted;

    @Schema(description = "样本数")
    @ExcelProperty("样本数")
    private Integer sampleCount;

    @Schema(description = "考核状态")
    @ExcelProperty("考核状态")
    private String inspectionStatus;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}