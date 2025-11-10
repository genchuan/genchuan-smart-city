package cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备实时状态 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceRealTimeStatusRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "状态ID")
    @ExcelProperty("状态ID")
    private String realTimeStatusId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编码")
    private String deviceCode;

    @Schema(description = "运行状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("运行状态")
    private String runStatus;

    @Schema(description = "最近上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最近上报时间")
    private LocalDateTime lastRptTime;

    @Schema(description = "实时参数1")
    @ExcelProperty("实时参数1")
    private String realTimeParam1;

    @Schema(description = "实时参数2")
    @ExcelProperty("实时参数2")
    private String realTimeParam2;

    @Schema(description = "状态更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("状态更新时间")
    private LocalDateTime statusUpdateTime;

    @Schema(description = "所属行政区划代码")
    @ExcelProperty("所属行政区划代码")
    private String regionCode;

    @Schema(description = "所属行政区划名称")
    @ExcelProperty("所属行政区划名称")
    private String regionName;

    @Schema(description = "所属网格ID")
    @ExcelProperty("所属网格ID")
    private String gridId;

    @Schema(description = "所属网格名称")
    @ExcelProperty("所属网格名称")
    private String gridName;

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