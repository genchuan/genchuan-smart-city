package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 充电收费 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkChargeFeeRespVO {

    @Schema(description = "[主键ID] 充电收费记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "12658")
    @ExcelProperty("[主键ID] 充电收费记录唯一标识")
    private Long id;

    @Schema(description = "[费率策略ID] 关联 park_fee_strategy.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23429")
    @ExcelProperty("[费率策略ID] 关联 park_fee_strategy.id")
    private Long feeStrategyId;

    @Schema(description = "[充电桩ID] 关联 tb_device_extend.device_extend_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13222")
    @ExcelProperty("[充电桩ID] 关联 tb_device_extend.device_extend_id")
    private Long chargePileId;

    @Schema(description = "[基础充电费率] 元/度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[基础充电费率] 元/度")
    private BigDecimal baseRate;

    @Schema(description = "[高峰充电费率] 元/度")
    @ExcelProperty("[高峰充电费率] 元/度")
    private BigDecimal peakRate;

    @Schema(description = "[平峰充电费率] 元/度")
    @ExcelProperty("[平峰充电费率] 元/度")
    private BigDecimal offPeakRate;

    @Schema(description = "[是否合并停车费] 如:0-否/1-是")
    @ExcelProperty("[是否合并停车费] 如:0-否/1-是")
    private Boolean mergeParkingFee;

    @Schema(description = "[活动ID] 关联 park_promotion.promotion_id，充电活动", example = "3321")
    @ExcelProperty("[活动ID] 关联 park_promotion.promotion_id，充电活动")
    private Long activityId;

    @Schema(description = "[状态] 如:启用/禁用", example = "1")
    @ExcelProperty("[状态] 如:启用/禁用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 充电收费相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 充电收费相关备注说明")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
