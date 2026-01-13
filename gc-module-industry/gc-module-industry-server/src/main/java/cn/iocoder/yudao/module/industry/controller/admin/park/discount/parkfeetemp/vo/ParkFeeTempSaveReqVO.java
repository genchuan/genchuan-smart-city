package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 临停收费规则新增/修改 Request VO")
@Data
public class ParkFeeTempSaveReqVO {

    @Schema(description = "[主键ID] 临停收费规则唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "8857")
    private Long id;

    @Schema(description = "[规则名称] 临停收费规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "[规则名称] 临停收费规则名称不能为空")
    private String ruleName;

    @Schema(description = "[适用车场ID列表] varchar，存储车场ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[适用车场ID列表] varchar，存储车场ID集合不能为空")
    private String lotIds;

    @Schema(description = "[费率策略ID] 关联费率策略 park_fee_strategy.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12790")
    @NotNull(message = "[费率策略ID] 关联费率策略 park_fee_strategy.id不能为空")
    private Long feeStrategyId;

    @Schema(description = "[免费停放时长] 单位：分钟")
    private Integer freeParkingTime;

    @Schema(description = "[单日最高费用] 超过该金额后封顶")
    private BigDecimal maxDailyFee;

    @Schema(description = "[优惠抵扣规则] 优惠抵扣规则描述，varchar")
    private String discountRule;

    @Schema(description = "[状态] 启用 / 禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 启用 / 禁用不能为空")
    private String status;

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
