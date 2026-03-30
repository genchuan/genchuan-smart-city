package cn.iocoder.yudao.module.waterdetection.controller.admin.testresult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 检测结果录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TestResultRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "样本编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("样本编号")
    private String sampleCode;

    @Schema(description = "检测指标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测指标")
    private String testIndicator;

    @Schema(description = "检测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测值")
    private Double testValue;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单位")
    private String unit;

    @Schema(description = "检测方法")
    @ExcelProperty("检测方法")
    private String testMethod;

    @Schema(description = "检测人员", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测人员")
    private String testOperator;

    @Schema(description = "检测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检测时间")
    private LocalDateTime testTime;

    @Schema(description = "设备编号")
    @ExcelProperty("设备编号")
    private String equipmentCode;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}