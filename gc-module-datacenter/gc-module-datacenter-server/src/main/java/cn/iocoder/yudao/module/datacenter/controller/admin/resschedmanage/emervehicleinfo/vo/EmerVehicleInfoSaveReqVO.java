package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 救援车辆信息新增/修改 Request VO")
@Data
public class EmerVehicleInfoSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31273")
    private Long id;

    @Schema(description = "车辆ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31493")
    @NotEmpty(message = "车辆ID，唯一编码，UUID不能为空")
    private String vehicleId;

    @Schema(description = "车辆编码，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车辆编码，系统自动生成不能为空")
    private String vehicleCode;

    @Schema(description = "车辆名称，标准名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "车辆名称，标准名称不能为空")
    private String vehicleName;

    @Schema(description = "分类ID，关联救援车辆分类配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "6899")
    @NotEmpty(message = "分类ID，关联救援车辆分类配置表不能为空")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "分类名称，与分类ID同步不能为空")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用区域代码，关联行政区划表不能为空")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "适用区域名称，与区域代码同步不能为空")
    private String applyRegionName;

    @Schema(description = "车辆型号，如“XX型救援车”")
    private String vehicleModel;

    @Schema(description = "车辆数量，该类型车辆的总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "13755")
    @NotNull(message = "车辆数量，该类型车辆的总数量不能为空")
    private Integer vehicleCount;

    @Schema(description = "可用数量，可投入救援的车辆数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "8699")
    @NotNull(message = "可用数量，可投入救援的车辆数量不能为空")
    private Integer availableVehicleCount;

    @Schema(description = "车辆状态，1（正常）/0（异常），默认1", example = "1")
    private String vehicleStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}