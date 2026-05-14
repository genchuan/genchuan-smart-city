package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 代付记录分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentRecordPageReqVO extends PageParam {

    @Schema(description = "记录编号，模糊查询")
    private String recordNo;

    @Schema(description = "关联订单ID")
    private Long orderId;


    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "商户名称，模糊查询")
    private String merchantName;

    @Schema(description = "状态：normal/abnormal")
    private String status;

    @Schema(description = "统计开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;
}
