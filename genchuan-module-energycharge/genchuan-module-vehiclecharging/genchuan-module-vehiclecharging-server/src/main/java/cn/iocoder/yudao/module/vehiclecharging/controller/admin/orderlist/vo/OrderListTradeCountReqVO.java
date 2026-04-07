// OrderListTradeCountReqVO.java
package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 订单交易统计钻取 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderListTradeCountReqVO extends OrderListDailyTrendReqVO {
    // 直接继承 startTime, endTime, stationId
}