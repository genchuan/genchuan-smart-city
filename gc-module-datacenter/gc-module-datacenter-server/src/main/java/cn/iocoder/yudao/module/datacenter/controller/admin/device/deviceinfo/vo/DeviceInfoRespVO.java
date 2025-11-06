package cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceInfoRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1573")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1100")
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("设备名称")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编码")
    private String deviceCode;

    @Schema(description = "设备类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("设备类型")
    private String deviceType;

    @Schema(description = "设备型号")
    @ExcelProperty("设备型号")
    private String deviceModel;

    @Schema(description = "设备规格")
    @ExcelProperty("设备规格")
    private String deviceSpec;

    @Schema(description = "生产厂商")
    @ExcelProperty("生产厂商")
    private String manufacturer;

    @Schema(description = "生产日期")
    @ExcelProperty("生产日期")
    private LocalDate productionDate;

    @Schema(description = "采购日期")
    @ExcelProperty("采购日期")
    private LocalDate purchaseDate;

    @Schema(description = "安装日期")
    @ExcelProperty("安装日期")
    private LocalDate installDate;

    @Schema(description = "质保期（月）")
    @ExcelProperty("质保期（月）")
    private Integer warrantyPeriod;

    @Schema(description = "设备状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("设备状态")
    private String deviceStatus;

    @Schema(description = "运行状态", example = "2")
    @ExcelProperty("运行状态")
    private String runStatus;

    @Schema(description = "最后在线时间")
    @ExcelProperty("最后在线时间")
    private LocalDateTime lastOnlineTime;

    @Schema(description = "设备大类ID", example = "27726")
    @ExcelProperty("设备大类ID")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", example = "李四")
    @ExcelProperty("设备大类名称")
    private String deviceMajorName;

    @Schema(description = "设备中类ID", example = "12388")
    @ExcelProperty("设备中类ID")
    private String deviceMidId;

    @Schema(description = "设备中类名称", example = "李四")
    @ExcelProperty("设备中类名称")
    private String deviceMidName;

    @Schema(description = "设备小类ID", example = "16664")
    @ExcelProperty("设备小类ID")
    private String deviceMinorId;

    @Schema(description = "设备小类名称", example = "芋艿")
    @ExcelProperty("设备小类名称")
    private String deviceMinorName;

    @Schema(description = "行政区划代码")
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", example = "王五")
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "网格ID", example = "19401")
    @ExcelProperty("网格ID")
    private String gridId;

    @Schema(description = "网格名称", example = "李四")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "网格编码")
    @ExcelProperty("网格编码")
    private String gridCode;

    @Schema(description = "监测部件ID", example = "9167")
    @ExcelProperty("监测部件ID")
    private String monCompId;

    @Schema(description = "监测部件名称", example = "张三")
    @ExcelProperty("监测部件名称")
    private String monCompName;

    @Schema(description = "监测部件编码")
    @ExcelProperty("监测部件编码")
    private String monCompCode;

    @Schema(description = "应用场景ID", example = "4498")
    @ExcelProperty("应用场景ID")
    private String appSceneId;

    @Schema(description = "应用场景名称", example = "赵六")
    @ExcelProperty("应用场景名称")
    private String appSceneName;

    @Schema(description = "应用场景编码")
    @ExcelProperty("应用场景编码")
    private String appSceneCode;

    @Schema(description = "资产ID", example = "4651")
    @ExcelProperty("资产ID")
    private String assetId;

    @Schema(description = "资产名称", example = "张三")
    @ExcelProperty("资产名称")
    private String assetName;

    @Schema(description = "资产编码")
    @ExcelProperty("资产编码")
    private String assetCode;

    @Schema(description = "主设备ID", example = "16649")
    @ExcelProperty("主设备ID")
    private String mainDeviceId;

    @Schema(description = "主设备名称", example = "王五")
    @ExcelProperty("主设备名称")
    private String mainDeviceName;

    @Schema(description = "主设备编码")
    @ExcelProperty("主设备编码")
    private String mainDeviceCode;

    @Schema(description = "安装位置描述")
    @ExcelProperty("安装位置描述")
    private String installLocation;

    @Schema(description = "坐标X（经度）")
    @ExcelProperty("坐标X（经度）")
    private BigDecimal coordX;

    @Schema(description = "坐标Y（纬度）")
    @ExcelProperty("坐标Y（纬度）")
    private BigDecimal coordY;

    @Schema(description = "高程")
    @ExcelProperty("高程")
    private BigDecimal elevation;

    @Schema(description = "定位精度（米）")
    @ExcelProperty("定位精度（米）")
    private BigDecimal positionAccuracy;

    @Schema(description = "IP地址")
    @ExcelProperty("IP地址")
    private String ipAddress;

    @Schema(description = "MAC地址")
    @ExcelProperty("MAC地址")
    private String macAddress;

    @Schema(description = "序列号")
    @ExcelProperty("序列号")
    private String serialNumber;

    @Schema(description = "固件版本")
    @ExcelProperty("固件版本")
    private String firmwareVersion;

    @Schema(description = "软件版本")
    @ExcelProperty("软件版本")
    private String softwareVersion;

    @Schema(description = "通信协议")
    @ExcelProperty("通信协议")
    private String communicationProtocol;

    @Schema(description = "数据上报间隔（秒）")
    @ExcelProperty("数据上报间隔（秒）")
    private Integer dataReportInterval;

    @Schema(description = "采样频率（秒）")
    @ExcelProperty("采样频率（秒）")
    private Integer samplingFrequency;

    @Schema(description = "电压等级")
    @ExcelProperty("电压等级")
    private String voltageLevel;

    @Schema(description = "供电方式")
    @ExcelProperty("供电方式")
    private String powerSupply;

    @Schema(description = "电池容量（mAh）")
    @ExcelProperty("电池容量（mAh）")
    private Integer batteryCapacity;

    @Schema(description = "电池电量（%）")
    @ExcelProperty("电池电量（%）")
    private Integer batteryLevel;

    @Schema(description = "信号强度")
    @ExcelProperty("信号强度")
    private Integer signalStrength;

    @Schema(description = "环境温度（℃）")
    @ExcelProperty("环境温度（℃）")
    private BigDecimal environmentTemp;

    @Schema(description = "环境湿度（%）")
    @ExcelProperty("环境湿度（%）")
    private BigDecimal environmentHumidity;

    @Schema(description = "维护周期（天）")
    @ExcelProperty("维护周期（天）")
    private Integer maintenanceCycle;

    @Schema(description = "最后维护日期")
    @ExcelProperty("最后维护日期")
    private LocalDateTime lastMaintenanceDate;

    @Schema(description = "下次维护日期")
    @ExcelProperty("下次维护日期")
    private LocalDateTime nextMaintenanceDate;

    @Schema(description = "维护单位")
    @ExcelProperty("维护单位")
    private String maintenanceOrg;

    @Schema(description = "维护人")
    @ExcelProperty("维护人")
    private String maintenancePerson;

    @Schema(description = "维护电话")
    @ExcelProperty("维护电话")
    private String maintenancePhone;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

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