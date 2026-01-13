package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 临停收费规则分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkFeeTempPageReqVO extends PageParam {

    @Schema(description = "[规则名称] 临停收费规则名称", example = "张三")
    private String ruleName;

    @Schema(description = "[适用车场ID列表] varchar，存储车场ID集合")
    private String lotIds;

    @Schema(description = "[费率策略ID] 关联费率策略 park_fee_strategy.id", example = "12790")
    private Long feeStrategyId;

    @Schema(description = "[免费停放时长] 单位：分钟")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] freeParkingTime;

    @Schema(description = "[单日最高费用] 超过该金额后封顶")
    private BigDecimal maxDailyFee;

    @Schema(description = "[优惠抵扣规则] 优惠抵扣规则描述，varchar")
    private String discountRule;

    @Schema(description = "[状态] 启用 / 禁用", example = "2")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 临停收费规则相关说明", example = "你说的对")
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
