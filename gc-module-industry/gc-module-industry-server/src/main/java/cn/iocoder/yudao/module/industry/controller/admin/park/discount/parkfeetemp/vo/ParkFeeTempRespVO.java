package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 临停收费规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkFeeTempRespVO {

    @Schema(description = "[主键ID] 临停收费规则唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "8857")
    @ExcelProperty("[主键ID] 临停收费规则唯一标识")
    private Long id;

    @Schema(description = "[规则名称] 临停收费规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("[规则名称] 临停收费规则名称")
    private String ruleName;

    @Schema(description = "[适用车场ID列表] varchar，存储车场ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[适用车场ID列表] varchar，存储车场ID集合")
    private String lotIds;

    @Schema(description = "[费率策略ID] 关联费率策略 park_fee_strategy.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12790")
    @ExcelProperty("[费率策略ID] 关联费率策略 park_fee_strategy.id")
    private Long feeStrategyId;

    @Schema(description = "[免费停放时长] 单位：分钟")
    @ExcelProperty("[免费停放时长] 单位：分钟")
    private Integer freeParkingTime;

    @Schema(description = "[单日最高费用] 超过该金额后封顶")
    @ExcelProperty("[单日最高费用] 超过该金额后封顶")
    private BigDecimal maxDailyFee;

    @Schema(description = "[优惠抵扣规则] 优惠抵扣规则描述，varchar")
    @ExcelProperty("[优惠抵扣规则] 优惠抵扣规则描述，varchar")
    private String discountRule;

    @Schema(description = "[状态] 启用 / 禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[状态] 启用 / 禁用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 临停收费规则相关说明", example = "你说的对")
    @ExcelProperty("[备注] 临停收费规则相关说明")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
