package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 退款订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkOrderRefundRespVO {

    @Schema(description = "主键ID，自增ID，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "15297")
    @ExcelProperty("主键ID，自增ID，记录唯一标识")
    private Long id;

    @Schema(description = "唯一退款单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("唯一退款单号")
    private String refundNo;

    @Schema(description = "关联退款的原订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1316")
    @ExcelProperty("关联退款的原订单ID")
    private Long originalOrderId;

    @Schema(description = "原订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("原订单编号")
    private String originalOrderNo;

    @Schema(description = "退款申请人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30440")
    @ExcelProperty("退款申请人ID")
    private Long userId;

    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "退款原因", example = "不对")
    @ExcelProperty("退款原因")
    private String refundReason;

    @Schema(description = "退款状态：申请中/已通过/已拒绝/已到账", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("退款状态：申请中/已通过/已拒绝/已到账")
    private String refundStatus;

    @Schema(description = "退款申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("退款申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditBy;

    @Schema(description = "到账时间")
    @ExcelProperty("到账时间")
    private LocalDateTime transferTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "备注信息", example = "你猜")
    @ExcelProperty("备注信息")
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

}
