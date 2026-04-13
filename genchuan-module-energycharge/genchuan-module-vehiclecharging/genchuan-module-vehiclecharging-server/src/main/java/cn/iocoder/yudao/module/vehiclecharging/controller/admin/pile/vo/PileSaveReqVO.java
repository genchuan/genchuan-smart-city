package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "汽车充电 - 充电桩新增/修改 Request VO")
@Data
public class PileSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "CP20250301002")
    @NotEmpty(message = "设备编号不能为空")
    private String pileCode;

    @Schema(description = "型号", requiredMode = Schema.RequiredMode.REQUIRED, example = "XD-60KW")
    @NotEmpty(message = "型号不能为空")
    private String model;

    @Schema(description = "功率（单位：kW）", requiredMode = Schema.RequiredMode.REQUIRED, example = "60.00")
    @NotNull(message = "功率（单位：kW）不能为空")
    private BigDecimal power;

    @Schema(description = "生产厂家", requiredMode = Schema.RequiredMode.REQUIRED, example = "星星充电")
    @NotEmpty(message = "生产厂家不能为空")
    private String manufacturer;

    @Schema(description = "所属场站ID，关联充电场站表charging_station", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "所属场站ID不能为空")
    private Long stationId;

    @Schema(description = "绑定车位ID，关联充电车位表charging_lot", example = "2002")
    private Long lotId;

    @Schema(description = "充电模式：1=直流，2=交流，3=交直流混合，关联 charge_mode 表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "充电模式不能为空")
    private Long chargeMode;

    @Schema(description = "设备状态：1=已启用，2=已停用，3=未调试，4=已调试，关联 pile_status 表")
    private Long pileStatus;

    @Schema(description = "故障标记：0-无故障，1-有故障")
    private Boolean faultFlag;

    @Schema(description = "运行时长（单位：小时）")
    private Integer runTime;

    @Schema(description = "充电枪二维码", example = "https://xxx.com/qrcode/CP20250301002.png")
    private String qrcode;

    @Schema(description = "备注", example = "A区2号快充桩")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}
