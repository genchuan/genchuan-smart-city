package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备客户端属性配置新增/修改 Request VO")
@Data
public class DeviceClientAttrSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "属性配置ID")
    private String clientAttrCfgId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性名称不能为空")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性代码不能为空")
    private String attrCode;

    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性值不能为空")
    private String attrValue;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否必选不能为空")
    private String isRequired;

    @Schema(description = "属性说明")
    private String attrDesc;

    @Schema(description = "配置时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "配置时间不能为空")
    private LocalDateTime cfgTime;

    @Schema(description = "配置人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "配置人不能为空")
    private String cfgUser;

    @Schema(description = "更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}