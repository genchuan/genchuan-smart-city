package cn.iocoder.yudao.module.park.controller.admin.park.pricing.feestrategy.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 费率策略 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FeeStrategyRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "644")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[策略名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[策略名称]")
    private String strategyName;

    @Schema(description = "[基础费率] 元/分钟")
    @ExcelProperty("[基础费率] 元/分钟")
    private BigDecimal baseRate;

    @Schema(description = "[单日最高费用]")
    @ExcelProperty("[单日最高费用]")
    private BigDecimal maxDailyFee;

    @Schema(description = "[适用范围] 如:全局/区域/车场")
    @ExcelProperty("[适用范围] 如:全局/区域/车场")
    private String applyScope;

    @Schema(description = "[适用范围ID列表] JSON格式varchar，区域ID/车场ID")
    @ExcelProperty("[适用范围ID列表] JSON格式varchar，区域ID/车场ID")
    private String scopeIds;

    @Schema(description = "[高峰时段] JSON格式varchar")
    @ExcelProperty("[高峰时段] JSON格式varchar")
    private String peakTime;

    @Schema(description = "[高峰费率] 元/分钟")
    @ExcelProperty("[高峰费率] 元/分钟")
    private BigDecimal peakRate;

    @Schema(description = "[平峰时段] JSON格式varchar")
    @ExcelProperty("[平峰时段] JSON格式varchar")
    private String offPeakTime;

    @Schema(description = "[平峰费率] 元/分钟")
    @ExcelProperty("[平峰费率] 元/分钟")
    private BigDecimal offPeakRate;

    @Schema(description = "[生效时间]")
    @ExcelProperty("[生效时间]")
    private LocalDateTime effectTime;

    @Schema(description = "[失效时间]")
    @ExcelProperty("[失效时间]")
    private LocalDateTime expireTime;

    @Schema(description = "[状态] 如:启用/禁用", example = "1")
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
