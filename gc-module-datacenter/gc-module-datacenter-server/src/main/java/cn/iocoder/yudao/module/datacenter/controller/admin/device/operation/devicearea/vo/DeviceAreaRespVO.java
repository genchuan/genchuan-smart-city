package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备关联行政区划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceAreaRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID")
    @ExcelProperty("关联ID")
    private String deviceRelRegionId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编码")
    private String deviceCode;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划级别")
    private String regionLevel;

    @Schema(description = "关联时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联时间")
    private LocalDateTime relTime;

    @Schema(description = "关联人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联人")
    private String relUser;

    @Schema(description = "关联状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联状态")
    private String relStatus;

    @Schema(description = "调整时间")
    @ExcelProperty("调整时间")
    private LocalDateTime adjustTime;

    @Schema(description = "调整人")
    @ExcelProperty("调整人")
    private String adjustUser;

    @Schema(description = "调整原因")
    @ExcelProperty("调整原因")
    private String adjustReason;

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