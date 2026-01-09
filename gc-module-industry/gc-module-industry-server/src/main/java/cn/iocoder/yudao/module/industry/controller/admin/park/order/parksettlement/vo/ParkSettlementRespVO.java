package cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 分账结算表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkSettlementRespVO {

    @Schema(description = "主键ID，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "23205")
    @ExcelProperty("主键ID，自增")
    private Long id;

    @Schema(description = "商户ID，商户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "506")
    @ExcelProperty("商户ID，商户唯一标识")
    private Long merchantId;

    @Schema(description = "结算总金额，本次结算的总金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("结算总金额，本次结算的总金额")
    private BigDecimal totalAmount;

    @Schema(description = "分账比例配置，JSON格式varchar")
    @ExcelProperty("分账比例配置，JSON格式varchar")
    private String splitRatio;

    @Schema(description = "平台分成金额，平台应分得的金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平台分成金额，平台应分得的金额")
    private BigDecimal platformAmount;

    @Schema(description = "商户分成金额，商户应分得的金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("商户分成金额，商户应分得的金额")
    private BigDecimal merchantAmount;

    @Schema(description = "税费金额，本次结算产生的税费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("税费金额，本次结算产生的税费金额")
    private BigDecimal taxAmount;

    @Schema(description = "结算状态：待生成 / 待审核 / 已结算 / 已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("结算状态：待生成 / 待审核 / 已结算 / 已驳回")
    private String settlementStatus;

    @Schema(description = "生成时间，结算数据生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生成时间，结算数据生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "审核时间，结算审核时间")
    @ExcelProperty("审核时间，结算审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核人，结算审核人")
    @ExcelProperty("审核人，结算审核人")
    private String auditBy;

    @Schema(description = "转账时间，结算转账完成时间")
    @ExcelProperty("转账时间，结算转账完成时间")
    private LocalDateTime transferTime;

    @Schema(description = "统计唯一标识码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计唯一标识码")
    private String statCode;

    @Schema(description = "统计周期标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期标识")
    private String statCycle;

    @Schema(description = "统计周期名称，如：2025年9月", example = "王五")
    @ExcelProperty("统计周期名称，如：2025年9月")
    private String statCycleName;

    @Schema(description = "统计区间起始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计区间起始时间")
    private LocalDateTime statStartTime;

    @Schema(description = "统计区间截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计区间截止时间")
    private LocalDateTime statEndTime;

    @Schema(description = "统计时间，实际统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间，实际统计时间")
    private LocalDateTime statTime;

    @Schema(description = "备注，业务备注说明", example = "随便")
    @ExcelProperty("备注，业务备注说明")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间，记录创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，记录创建时间")
    private LocalDateTime createTime;

}
