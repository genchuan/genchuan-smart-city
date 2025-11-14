package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备遥测数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceTelemetryDataRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "遥测数据ID")
    @ExcelProperty("遥测数据ID")
    private String telemetryDataId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编码")
    private String deviceCode;

    @Schema(description = "遥测项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("遥测项名称")
    private String telemetryItemName;

    @Schema(description = "遥测项代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("遥测项代码")
    private String telemetryItemCode;

    @Schema(description = "遥测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("遥测值")
    private String telemetryValue;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据类型")
    private String dataType;

    @Schema(description = "采集时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采集时间")
    private LocalDateTime collectTime;

    @Schema(description = "数据来源", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据来源")
    private String dataSource;

    @Schema(description = "存储周期(天)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("存储周期(天)")
    private Integer storageCycle;

    @Schema(description = "过期状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("过期状态")
    private String expireStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTimeSys;

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