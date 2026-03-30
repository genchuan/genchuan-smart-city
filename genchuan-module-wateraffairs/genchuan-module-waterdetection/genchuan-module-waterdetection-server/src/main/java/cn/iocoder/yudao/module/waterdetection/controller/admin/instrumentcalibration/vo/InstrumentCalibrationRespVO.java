package cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 仪器零点/量程漂移校验 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InstrumentCalibrationRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "仪器ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("仪器ID")
    private String instrumentId;

    @Schema(description = "校验日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验日期")
    private LocalDateTime calibrationDate;

    @Schema(description = "零点校正液浓度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("零点校正液浓度")
    private Double zeroPointConc;

    @Schema(description = "零点漂移值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("零点漂移值")
    private Double zeroDrift;

    @Schema(description = "量程校正液浓度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("量程校正液浓度")
    private Double spanConc;

    @Schema(description = "量程漂移值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("量程漂移值")
    private Double spanDrift;

    @Schema(description = "校验结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验结果")
    private String calibrationResult;

    @Schema(description = "操作人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作人员ID")
    private String operatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}