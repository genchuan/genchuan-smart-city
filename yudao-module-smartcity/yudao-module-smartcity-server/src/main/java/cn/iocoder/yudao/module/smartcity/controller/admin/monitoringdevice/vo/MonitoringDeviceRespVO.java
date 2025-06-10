package cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 监测设备 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MonitoringDeviceRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "26353")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "设备编号")
    @ExcelProperty("设备编号")
    private String deviceNumber;

    @Schema(description = "设备名称", example = "李四")
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备类型", example = "2")
    @ExcelProperty("设备类型")
    private String deviceType;

    @Schema(description = "生产厂家")
    @ExcelProperty("生产厂家")
    private String manufacturer;

    @Schema(description = "维护记录")
    @ExcelProperty("维护记录")
    private String maintenanceRecord;

    @Schema(description = "安装时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("安装时间")
    private LocalDateTime createTime;

}