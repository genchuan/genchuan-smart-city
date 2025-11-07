package cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备信息新增/修改 Request VO")
@Data
public class DeviceInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1573")
    private Long id;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1100")
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "设备类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "设备类型不能为空")
    private String deviceType;

    @Schema(description = "设备型号")
    private String deviceModel;

    @Schema(description = "设备规格")
    private String deviceSpec;

    @Schema(description = "生产厂商")
    private String manufacturer;

    @Schema(description = "生产日期")
    private LocalDate productionDate;

    @Schema(description = "采购日期")
    private LocalDate purchaseDate;

    @Schema(description = "安装日期")
    private LocalDate installDate;

    @Schema(description = "质保期（月）")
    private Integer warrantyPeriod;

    @Schema(description = "设备状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "设备状态不能为空")
    private String deviceStatus;

    @Schema(description = "运行状态", example = "2")
    private String runStatus;

    @Schema(description = "最后在线时间")
    private LocalDateTime lastOnlineTime;

    @Schema(description = "设备大类ID", example = "27726")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", example = "李四")
    private String deviceMajorName;

    @Schema(description = "设备中类ID", example = "12388")
    private String deviceMidId;

    @Schema(description = "设备中类名称", example = "李四")
    private String deviceMidName;

    @Schema(description = "设备小类ID", example = "16664")
    private String deviceMinorId;

    @Schema(description = "设备小类名称", example = "芋艿")
    private String deviceMinorName;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", example = "王五")
    private String regionName;

    @Schema(description = "网格ID", example = "19401")
    private String gridId;

    @Schema(description = "网格名称", example = "李四")
    private String gridName;

    @Schema(description = "网格编码")
    private String gridCode;

    @Schema(description = "监测部件ID", example = "9167")
    private String monCompId;

    @Schema(description = "监测部件名称", example = "张三")
    private String monCompName;

    @Schema(description = "监测部件编码")
    private String monCompCode;

    @Schema(description = "应用场景ID", example = "4498")
    private String appSceneId;

    @Schema(description = "应用场景名称", example = "赵六")
    private String appSceneName;

    @Schema(description = "应用场景编码")
    private String appSceneCode;

    @Schema(description = "资产ID", example = "4651")
    private String assetId;

    @Schema(description = "资产名称", example = "张三")
    private String assetName;

    @Schema(description = "资产编码")
    private String assetCode;

    @Schema(description = "主设备ID", example = "16649")
    private String mainDeviceId;

    @Schema(description = "主设备名称", example = "王五")
    private String mainDeviceName;

    @Schema(description = "主设备编码")
    private String mainDeviceCode;

    @Schema(description = "安装位置描述")
    private String installLocation;

    @Schema(description = "坐标X（经度）")
    private BigDecimal coordX;

    @Schema(description = "坐标Y（纬度）")
    private BigDecimal coordY;

    @Schema(description = "高程")
    private BigDecimal elevation;

    @Schema(description = "定位精度（米）")
    private BigDecimal positionAccuracy;

    @Schema(description = "IP地址")
    private String ipAddress;

    @Schema(description = "MAC地址")
    private String macAddress;

    @Schema(description = "序列号")
    private String serialNumber;

    @Schema(description = "固件版本")
    private String firmwareVersion;

    @Schema(description = "软件版本")
    private String softwareVersion;

    @Schema(description = "通信协议")
    private String communicationProtocol;

    @Schema(description = "数据上报间隔（秒）")
    private Integer dataReportInterval;

    @Schema(description = "采样频率（秒）")
    private Integer samplingFrequency;

    @Schema(description = "电压等级")
    private String voltageLevel;

    @Schema(description = "供电方式")
    private String powerSupply;

    @Schema(description = "电池容量（mAh）")
    private Integer batteryCapacity;

    @Schema(description = "电池电量（%）")
    private Integer batteryLevel;

    @Schema(description = "信号强度")
    private Integer signalStrength;

    @Schema(description = "环境温度（℃）")
    private BigDecimal environmentTemp;

    @Schema(description = "环境湿度（%）")
    private BigDecimal environmentHumidity;

    @Schema(description = "维护周期（天）")
    private Integer maintenanceCycle;

    @Schema(description = "最后维护日期")
    private LocalDateTime lastMaintenanceDate;

    @Schema(description = "下次维护日期")
    private LocalDateTime nextMaintenanceDate;

    @Schema(description = "维护单位")
    private String maintenanceOrg;

    @Schema(description = "维护人")
    private String maintenancePerson;

    @Schema(description = "维护电话")
    private String maintenancePhone;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}