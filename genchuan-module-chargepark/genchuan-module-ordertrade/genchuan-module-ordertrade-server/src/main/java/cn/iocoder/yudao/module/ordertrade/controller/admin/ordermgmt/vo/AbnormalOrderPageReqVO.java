package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - AbnormalOrder 分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AbnormalOrderPageReqVO extends PageParam {

    @Schema(description = "关联订单ID")
    private Long orderId;
    @Schema(description = "订单类型")
    private String orderType;
    @Schema(description = "异常类型")
    private String abnormalType;
    @Schema(description = "处置状态")
    private String status;
    @Schema(description = "场站ID")
    private Long stationId;
    @Schema(description = "识别时间范围")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] identifyTime;
}
