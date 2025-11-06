package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 救援设备信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EmerDeviceInfoRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8655")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "设备ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26573")
    @ExcelProperty("设备ID，唯一编码，UUID")
    private String deviceId;

    @Schema(description = "设备编码，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编码，系统自动生成")
    private String deviceCode;

    @Schema(description = "设备名称，标准名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("设备名称，标准名称")
    private String deviceName;

    @Schema(description = "分类ID，关联救援设备分类配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "24923")
    @ExcelProperty("分类ID，关联救援设备分类配置表")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("分类名称，与分类ID同步")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("适用区域名称，与区域代码同步")
    private String applyRegionName;

    @Schema(description = "设备型号，如“XX型救援设备”")
    @ExcelProperty("设备型号，如“XX型救援设备”")
    private String deviceModel;

    @Schema(description = "设备数量，该类型设备的总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "614")
    @ExcelProperty("设备数量，该类型设备的总数量")
    private Integer deviceCount;

    @Schema(description = "可用数量，可投入救援的设备数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "22219")
    @ExcelProperty("可用数量，可投入救援的设备数量")
    private Integer availableDeviceCount;

    @Schema(description = "设备状态，1（正常）/0（异常），默认1", example = "1")
    @ExcelProperty("设备状态，1（正常）/0（异常），默认1")
    private String deviceStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    @ExcelProperty("创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @ExcelProperty("创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    @ExcelProperty("更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}