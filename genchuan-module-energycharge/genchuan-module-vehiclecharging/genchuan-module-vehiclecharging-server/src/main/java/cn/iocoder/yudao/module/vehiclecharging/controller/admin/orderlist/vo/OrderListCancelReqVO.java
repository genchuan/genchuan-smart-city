package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 取消订单 Request VO")
@Data
public class OrderListCancelReqVO extends OrderListSaveReqVO {
}
