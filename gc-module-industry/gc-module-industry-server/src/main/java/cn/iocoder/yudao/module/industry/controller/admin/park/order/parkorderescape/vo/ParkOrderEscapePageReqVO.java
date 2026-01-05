package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 逃费订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkOrderEscapePageReqVO extends PageParam {

    @Schema(description = "该订单编号")
    private String orderNo;

    @Schema(description = "逃费订单ID / 原订单ID，关联 park_order_temp.order_id 或 park_order_period.order_id", example = "19527")
    private Long escapeOrderId;

    @Schema(description = "车辆车牌号")
    private String carNumber;

    @Schema(description = "用户ID，关联 sys_user.id，可为空", example = "2390")
    private Long userId;

    @Schema(description = "车场ID，关联 park_lot.lot_id", example = "17870")
    private Long lotId;

    @Schema(description = "发生逃费的时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] escapeTime;

    @Schema(description = "逃费金额")
    private BigDecimal escapeAmount;

    @Schema(description = "逃费原因，包括：未缴费离场/设备故障/其他", example = "不好")
    private String escapeReason;

    @Schema(description = "追缴状态，包括：未追缴/追缴中/已追缴/无法追缴", example = "2")
    private String traceStatus;

    @Schema(description = "追缴次数")
    private Integer traceTimes;

    @Schema(description = "上次追缴时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastTraceTime;

    @Schema(description = "追缴方式，包括：短信提醒/电话通知/系统限制/法律途径")
    private String traceWay;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "备注信息", example = "随便")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
