package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 救援设备信息新增/修改 Request VO")
@Data
public class EmerDeviceInfoSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8655")
    private Long id;

    @Schema(description = "设备ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26573")
    @NotEmpty(message = "设备ID，唯一编码，UUID不能为空")
    private String deviceId;

    @Schema(description = "设备编码，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码，系统自动生成不能为空")
    private String deviceCode;

    @Schema(description = "设备名称，标准名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "设备名称，标准名称不能为空")
    private String deviceName;

    @Schema(description = "分类ID，关联救援设备分类配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "24923")
    @NotEmpty(message = "分类ID，关联救援设备分类配置表不能为空")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "分类名称，与分类ID同步不能为空")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用区域代码，关联行政区划表不能为空")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "适用区域名称，与区域代码同步不能为空")
    private String applyRegionName;

    @Schema(description = "设备型号，如“XX型救援设备”")
    private String deviceModel;

    @Schema(description = "设备数量，该类型设备的总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "614")
    @NotNull(message = "设备数量，该类型设备的总数量不能为空")
    private Integer deviceCount;

    @Schema(description = "可用数量，可投入救援的设备数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "22219")
    @NotNull(message = "可用数量，可投入救援的设备数量不能为空")
    private Integer availableDeviceCount;

    @Schema(description = "设备状态，1（正常）/0（异常），默认1", example = "1")
    private String deviceStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}