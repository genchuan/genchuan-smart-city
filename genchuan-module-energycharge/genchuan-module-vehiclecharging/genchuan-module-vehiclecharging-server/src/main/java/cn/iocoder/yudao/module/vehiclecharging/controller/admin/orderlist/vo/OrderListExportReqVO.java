package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo.InterconnectionPageReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 订单列表表导出 Excel Request VO")
@Data
public class OrderListExportReqVO extends OrderListPageReqVO {

}
