package cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 环卫设施安全 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnvironmentalSafetyRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24563")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "设施名称", example = "芋艿")
    @ExcelProperty("设施名称")
    private String facilityName;

    @Schema(description = "设施类型", example = "2")
    @ExcelProperty("设施类型")
    private String facilityType;

    @Schema(description = "设施位置")
    @ExcelProperty("设施位置")
    private String facilityLocation;

    @Schema(description = "设施编号")
    @ExcelProperty("设施编号")
    private String facilityNumber;

    @Schema(description = "运行状态")
    @ExcelProperty("运行状态")
    private String runningState;

    @Schema(description = "故障代码")
    @ExcelProperty("故障代码")
    private String faultCode;

    @Schema(description = "维修记录")
    @ExcelProperty("维修记录")
    private String maintenanceRecord;

    @Schema(description = "维修时间")
    @ExcelProperty("维修时间")
    private String maintenanceTime;

    @Schema(description = "异味浓度")
    @ExcelProperty("异味浓度")
    private String odorConcentration;

    @Schema(description = "有害气体含量")
    @ExcelProperty("有害气体含量")
    private String harmfulGasContent;

    @Schema(description = "消毒记录")
    @ExcelProperty("消毒记录")
    private String disinfectionRecord;

    @Schema(description = "蝇虫密度")
    @ExcelProperty("蝇虫密度")
    private String flyAndInsectDensity;

    @Schema(description = "设施使用频率")
    @ExcelProperty("设施使用频率")
    private String facilityUsageFrequency;

    @Schema(description = "投诉记录")
    @ExcelProperty("投诉记录")
    private String complaintRecord;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}