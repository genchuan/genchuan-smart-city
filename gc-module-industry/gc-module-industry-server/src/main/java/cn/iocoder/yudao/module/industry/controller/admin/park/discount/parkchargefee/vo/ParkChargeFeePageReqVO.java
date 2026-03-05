package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 充电收费分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkChargeFeePageReqVO extends PageParam {

    @Schema(description = "[费率策略ID] 关联 park_fee_strategy.id", example = "23429")
    private Long feeStrategyId;

    @Schema(description = "[充电桩ID] 关联 tb_device_extend.device_extend_id", example = "13222")
    private Long chargePileId;

    @Schema(description = "[基础充电费率] 元/度")
    private BigDecimal baseRate;

    @Schema(description = "[高峰充电费率] 元/度")
    private BigDecimal peakRate;

    @Schema(description = "[平峰充电费率] 元/度")
    private BigDecimal offPeakRate;

    @Schema(description = "[是否合并停车费] 如:0-否/1-是")
    private Boolean mergeParkingFee;

    @Schema(description = "[活动ID] 关联 park_promotion.promotion_id，充电活动", example = "3321")
    private Long activityId;

    @Schema(description = "[状态] 如:启用/禁用", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 充电收费相关备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
