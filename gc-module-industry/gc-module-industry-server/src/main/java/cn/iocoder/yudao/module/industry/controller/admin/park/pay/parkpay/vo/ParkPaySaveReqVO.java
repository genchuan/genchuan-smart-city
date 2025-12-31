package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 停车缴费服务新增/修改 Request VO")
@Data
public class ParkPaySaveReqVO {

    @Schema(description = "主键ID，唯一标识", example = "1")
    private Long id;

    @Schema(description = "缴费记录唯一标识", example = "550e8400e29b41d4a716446655440000")
    @NotEmpty(message = "缴费记录唯一标识不能为空")
    private String payCode;

    @Schema(description = "关联订单编号", example = "WO20251226001")
    private String woNo;

    @Schema(description = "关联预约记录ID", example = "1")
    private Long reservationId;

    @Schema(description = "支付订单编号", example = "PAY20251226001")
    private String payWoNo;

    @Schema(description = "停车场唯一标识", example = "1")
    @NotNull(message = "停车场唯一标识不能为空")
    private Long parkLotId;

    @Schema(description = "停车场名称", example = "尚方停车场")
    @NotEmpty(message = "停车场名称不能为空")
    private String parkLotName;

    @Schema(description = "泊位唯一标识", example = "1")
    private Long berthId;

    @Schema(description = "泊位编号", example = "BO10001")
    private String berthNo;

    @Schema(description = "车辆车牌号", example = "闽A12345")
    @NotEmpty(message = "车辆车牌号不能为空")
    private String plateNum;

    @Schema(description = "车辆入场时间", example = "2025-12-26T08:00:00")
    private LocalDateTime entryTime;

    @Schema(description = "车辆出场时间", example = "2025-12-26T10:30:00")
    private LocalDateTime exitTime;

    @Schema(description = "停车持续时间（分钟）", example = "150")
    private Integer parkEndure;

    @Schema(description = "支付完成时间", example = "2025-12-26T10:35:00")
    private LocalDateTime payTime;

    @Schema(description = "应收金额", example = "50.00")
    private BigDecimal receivableAmount;

    @Schema(description = "优惠金额", example = "5.00")
    private BigDecimal discountAmount;

    @Schema(description = "实际支付金额", example = "45.00")
    private BigDecimal actualPayAmount;

    @Schema(description = "支付方式(微信支付/支付宝支付/银行卡支付)", example = "微信支付")
    private String payMethod;

    @Schema(description = "支付状态(待支付/支付成功/支付失败)", example = "待支付")
    private String payStatus;

    @Schema(description = "放行状态", example = "已放行")
    private String releaseStatus;

    @Schema(description = "优惠活动名称", example = "新用户优惠")
    private String activityName;

    @Schema(description = "行政区域规划国家标准12位", example = "350100000000")
    private String regionFullCode;

    @Schema(description = "行政区划名称", example = "福建省福州市鼓楼区")
    private String regionName;

    @Schema(description = "网格名称", example = "鼓楼网格1")
    private String gridName;

    @Schema(description = "分类扩展字段1", example = "扩展1")
    private String extCat1;

    @Schema(description = "分类扩展字段2", example = "扩展2")
    private String extCat2;

    @Schema(description = "通用扩展字段1", example = "通用1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2", example = "通用2")
    private String extCommon2;
}
