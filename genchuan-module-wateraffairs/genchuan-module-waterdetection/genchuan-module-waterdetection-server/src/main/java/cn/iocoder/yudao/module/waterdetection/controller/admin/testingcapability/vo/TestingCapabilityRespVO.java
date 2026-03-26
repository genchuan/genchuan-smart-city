package cn.iocoder.yudao.module.waterdetection.controller.admin.testingcapability.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 检测能力及设备管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TestingCapabilityRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("机构编号")
    private String agencyCode;

    @Schema(description = "可检测指标")
    @ExcelProperty("可检测指标")
    private String testableIndicators;

    @Schema(description = "设备型号")
    @ExcelProperty("设备型号")
    private String equipmentModel;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编号")
    private String equipmentNo;

    @Schema(description = "校准记录")
    @ExcelProperty("校准记录")
    private String calibrationRecord;

    @Schema(description = "设备状态(正常/维修中/停用)")
    @ExcelProperty("设备状态(正常/维修中/停用)")
    private String equipmentStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}