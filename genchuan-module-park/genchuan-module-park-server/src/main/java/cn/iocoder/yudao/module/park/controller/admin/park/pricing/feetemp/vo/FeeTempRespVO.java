package cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 临停收费规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FeeTempRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "4449")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[规则名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("[规则名称]")
    private String ruleName;

    @Schema(description = "[费率策略ID] 关联park_fee_strategy.id", example = "29200")
    @ExcelProperty("[费率策略ID] 关联park_fee_strategy.id")
    private Long feeStrategyId;

    @Schema(description = "[免费停放时长] 分钟")
    @ExcelProperty("[免费停放时长] 分钟")
    private Integer freeParkingTime;

    @Schema(description = "[单日最高费用] 优先级高于费率策略")
    @ExcelProperty("[单日最高费用] 优先级高于费率策略")
    private BigDecimal maxDailyFee;

    @Schema(description = "[适用车场ID列表] JSON格式varchar，关联park_lot.id")
    @ExcelProperty("[适用车场ID列表] JSON格式varchar，关联park_lot.id")
    private String applyLotIds;

    @Schema(description = "[状态] 如:启用/禁用", example = "2")
    @ExcelProperty("[状态] 如:启用/禁用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "你说的对")
    @ExcelProperty("[备注]")
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
