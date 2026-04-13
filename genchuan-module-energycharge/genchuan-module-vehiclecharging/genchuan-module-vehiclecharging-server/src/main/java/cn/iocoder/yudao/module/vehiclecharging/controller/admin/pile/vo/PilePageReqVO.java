package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "汽车充电 - 充电桩分页 Request VO")
@Data
public class PilePageReqVO extends PageParam {

    @Schema(description = "设备编号")
    private String pileCode;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "功率（单位：kW）")
    private BigDecimal power;

    @Schema(description = "生产厂家")
    private String manufacturer;

    @Schema(description = "所属场站ID，关联充电场站表charging_station", example = "27260")
    private Long stationId;

    @Schema(description = "绑定车位ID，关联充电车位表charging_lot", example = "13271")
    private Long lotId;

    @Schema(description = "充电模式：1=直流，2=交流，3=交直流混合，关联 charge_mode 表")
    private Long chargeMode;

    @Schema(description = "设备状态：1=已启用，2=已停用，3=未调试，4=已调试，关联 pile_status 表", example = "2")
    private Long pileStatus;

    @Schema(description = "故障标记：0-无故障，1-有故障")
    private Boolean faultFlag;

    @Schema(description = "运行时长（单位：小时）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] runTime;

    @Schema(description = "充电枪二维码")
    private String qrcode;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "删除标识：0-未删除，1-已删除")
    private Boolean deleted;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}