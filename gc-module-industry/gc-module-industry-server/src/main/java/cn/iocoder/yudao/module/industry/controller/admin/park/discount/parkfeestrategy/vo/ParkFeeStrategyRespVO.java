package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 费率策略 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkFeeStrategyRespVO {

    @Schema(description = "[主键ID] 费率策略唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "10157")
    @ExcelProperty("[主键ID] 费率策略唯一标识")
    private Long id;

    @Schema(description = "[策略名称] 费率策略名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[策略名称] 费率策略名称")
    private String strategyName;

    @Schema(description = "[策略类型] 基础费率 / 时段费率 / 区域费率", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[策略类型] 基础费率 / 时段费率 / 区域费率")
    private String strategyType;

    @Schema(description = "[适用范围] 全局 / 区域 / 车场，JSON 格式varchar")
    @ExcelProperty("[适用范围] 全局 / 区域 / 车场，JSON 格式varchar")
    private String applyScope;

    @Schema(description = "[基础费率] 元/分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[基础费率] 元/分钟")
    private BigDecimal baseRate;

    @Schema(description = "[高峰费率] 元/分钟，仅时段费率策略适用")
    @ExcelProperty("[高峰费率] 元/分钟，仅时段费率策略适用")
    private BigDecimal peakRate;

    @Schema(description = "[平峰费率] 元/分钟，仅时段费率策略适用")
    @ExcelProperty("[平峰费率] 元/分钟，仅时段费率策略适用")
    private BigDecimal offPeakRate;

    @Schema(description = "[区域费率配置] JSON 格式，仅区域费率策略适用")
    @ExcelProperty("[区域费率配置] JSON 格式，仅区域费率策略适用")
    private String regionRate;

    @Schema(description = "[生效时间] 策略生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[生效时间] 策略生效时间")
    private LocalDateTime startTime;

    @Schema(description = "[失效时间] 策略失效时间，永久有效为 NULL")
    @ExcelProperty("[失效时间] 策略失效时间，永久有效为 NULL")
    private LocalDateTime endTime;

    @Schema(description = "[状态] 启用 / 禁用", example = "2")
    @ExcelProperty("[状态] 启用 / 禁用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 费率策略相关备注说明", example = "你猜")
    @ExcelProperty("[备注] 费率策略相关备注说明")
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
