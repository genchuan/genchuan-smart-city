package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.VO;

import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charginglot.ChargingLotDO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;

@Schema(description = "汽车充电 - 充电桩 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PileRespVO implements VO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13504")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编号")
    private String pileCode;

    @Schema(description = "型号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("型号")
    private String model;

    @Schema(description = "功率（单位：kW）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("功率（单位：kW）")
    private BigDecimal power;

    @Schema(description = "生产厂家", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生产厂家")
    private String manufacturer;

    @Schema(description = "所属场站ID，关联充电场站表charging_station", requiredMode = Schema.RequiredMode.REQUIRED, example = "27260")
    @ExcelProperty("所属场站ID，关联充电场站表charging_station")
    @Trans(type = TransType.SIMPLE, target = ChargingStationDO.class, fields = "stationName", ref = "stationName")
    private Long stationId;

    @Schema(description = "所属场站名称")
    private String stationName;

    @Schema(description = "绑定车位ID，关联充电车位表charging_lot", example = "13271")
    @ExcelProperty("绑定车位ID，关联充电车位表charging_lot")
    @Trans(type = TransType.SIMPLE, target = ChargingLotDO.class, fields = "lotCode", ref = "lotName")
    private Long lotId;

    @Schema(description = "车位编号（名称）")
    private String lotName;

    @Schema(description = "充电模式：1=直流，2=交流，3=交直流混合，关联 charge_mode 表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充电模式：1=直流，2=交流，3=交直流混合，关联 charge_mode 表")
    private Long chargeMode;

    @Schema(description = "充电模式名称")
    private String chargeModeName;

    @Schema(description = "设备状态：1=已启用，2=已停用，3=未调试，4=已调试，关联 pile_status 表", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("设备状态：1=已启用，2=已停用，3=未调试，4=已调试，关联 pile_status 表")
    private Long pileStatus;

    @Schema(description = "设备状态名称")
    private String pileStatusName;

    @Schema(description = "故障标记：0-无故障，1-有故障", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("故障标记：0-无故障，1-有故障")
    private Boolean faultFlag;

    @Schema(description = "运行时长（单位：小时）")
    @ExcelProperty("运行时长（单位：小时）")
    private Integer runTime;

    @Schema(description = "充电枪二维码")
    @ExcelProperty("充电枪二维码")
    private String qrcode;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建者")
    @Trans(type = TransType.AUTO_TRANS, key = AdminUserApi.PREFIX, fields = "nickname", ref = "creatorName")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新者")
    @Trans(type = TransType.AUTO_TRANS, key = AdminUserApi.PREFIX, fields = "nickname", ref = "updaterName")
    private String updater;

    @Schema(description = "更新者名称")
    private String updaterName;

    @Schema(description = "删除标识：0-未删除，1-已删除", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("删除标识：0-未删除，1-已删除")
    private Boolean deleted;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}