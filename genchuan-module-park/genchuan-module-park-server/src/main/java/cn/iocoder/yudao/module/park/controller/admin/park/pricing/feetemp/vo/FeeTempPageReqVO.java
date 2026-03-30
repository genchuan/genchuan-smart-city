package cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 临停收费规则分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeeTempPageReqVO extends PageParam {

    @Schema(description = "[规则名称]", example = "张三")
    private String ruleName;

    @Schema(description = "[费率策略ID] 关联park_fee_strategy.id", example = "29200")
    private Long feeStrategyId;

    @Schema(description = "[免费停放时长] 分钟")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] freeParkingTime;

    @Schema(description = "[单日最高费用] 优先级高于费率策略")
    private BigDecimal maxDailyFee;

    @Schema(description = "[适用车场ID列表] JSON格式varchar，关联park_lot.id")
    private String applyLotIds;

    @Schema(description = "[状态] 如:启用/禁用", example = "2")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
