package cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 排水安全 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DrainageSafetyRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "3160")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "预警编号")
    @ExcelProperty("预警编号")
    private String warningNumber;

    @Schema(description = "预警时间")
    @ExcelProperty("预警时间")
    private String warningTime;

    @Schema(description = "预警级别")
    @ExcelProperty("预警级别")
    private String warningLevel;

    @Schema(description = "预警区域")
    @ExcelProperty("预警区域")
    private String warningArea;

    @Schema(description = "监测点位")
    @ExcelProperty("监测点位")
    private String monitoringPoints;

    @Schema(description = "实时水位")
    @ExcelProperty("实时水位")
    private String realTimeWaterLevel;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}