package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车缴费服务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPayPageReqVO extends PageParam {

    @Schema(description = "缴费记录唯一标识", example = "19728")
    private String payCode;

    @Schema(description = "关联订单编号")
    private String woNo;

    @Schema(description = "关联预约记录ID", example = "4022")
    private Long reservationId;

    @Schema(description = "支付订单编号")
    private String payWoNo;

    @Schema(description = "停车场唯一标识", example = "1")
    private Long parkLotId;

    @Schema(description = "停车场名称", example = "王五")
    private String parkLotName;

    @Schema(description = "泊位唯一标识", example = "14927")
    private Long berthId;

    @Schema(description = "泊位编号")
    private String berthNo;

    @Schema(description = "车辆车牌号")
    private String plateNum;

    @Schema(description = "车辆入场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryTime;

    @Schema(description = "车辆出场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] exitTime;

    @Schema(description = "停车持续时间（分钟）")
    private Integer parkEndure;

    @Schema(description = "支付完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] payTime;

    @Schema(description = "应收金额")
    private BigDecimal receivableAmount;

    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "实际支付金额")
    private BigDecimal actualPayAmount;

    @Schema(description = "支付方式")
    private String payMethod;

    @Schema(description = "支付状态", example = "1")
    private String payStatus;

    @Schema(description = "放行状态", example = "1")
    private String releaseStatus;

    @Schema(description = "优惠活动名称", example = "张三")
    private String activityName;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "行政区划名称", example = "芋艿")
    private String regionName;

    @Schema(description = "网格名称", example = "张三")
    private String gridName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    // ----------------- 新增筛选字段 -----------------
    @Schema(description = "统计区间起始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计区间截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

    @Schema(description = "统计行政区划全码（12位）", example = "350100000000")
    private String regionFullCode;

}
