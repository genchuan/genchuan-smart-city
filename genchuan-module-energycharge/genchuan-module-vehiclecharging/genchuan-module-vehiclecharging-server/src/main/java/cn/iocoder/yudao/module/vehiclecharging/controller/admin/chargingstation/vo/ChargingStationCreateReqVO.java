package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "充电站 - 创建场站 Request VO")
@Data
public class ChargingStationCreateReqVO {

    @NotBlank(message = "场站编号不能为空")
    @Schema(description = "场站编号，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stationCode;

    @NotBlank(message = "场站名称不能为空")
    @Schema(description = "场站名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stationName;

    @NotBlank(message = "场站地址不能为空")
    @Schema(description = "场站地址", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @NotBlank(message = "合作模式不能为空")
    @Schema(description = "合作模式（自营/联营/加盟）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String coopMode;

    @NotBlank(message = "开放时间不能为空")
    @Schema(description = "开放时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private String openTime;

    @NotNull(message = "电价服务费不能为空")
    @Schema(description = "电价服务费", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal priceService;

    @NotBlank(message = "负责人不能为空")
    @Schema(description = "负责人", requiredMode = Schema.RequiredMode.REQUIRED)
    private String manager;

    @Schema(description = "经度")
    private BigDecimal lon;

    @Schema(description = "纬度")
    private BigDecimal lat;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}