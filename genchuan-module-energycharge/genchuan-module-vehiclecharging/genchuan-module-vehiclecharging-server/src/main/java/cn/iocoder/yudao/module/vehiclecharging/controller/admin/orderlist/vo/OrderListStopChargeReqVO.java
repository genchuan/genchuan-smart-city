package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 终止充电 Request VO")
@Data
public class OrderListStopChargeReqVO extends OrderListSaveReqVO {
}
