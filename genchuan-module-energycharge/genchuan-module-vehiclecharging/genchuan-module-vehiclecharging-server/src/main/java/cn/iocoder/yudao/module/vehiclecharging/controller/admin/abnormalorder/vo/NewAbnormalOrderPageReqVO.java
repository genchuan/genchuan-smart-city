package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "异常订单分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class NewAbnormalOrderPageReqVO extends PageParam {

    @Schema(description = "异常编号，模糊匹配", example = "ABN-2025")
    private String abnormalCode;

    @Schema(description = "订单编号，模糊匹配", example = "ORD-2025")
    private String orderCode;

    @Schema(description = "车牌号，模糊匹配", example = "闽C12345")
    private String plateNo;

    @Schema(description = "异常类型", example = "充电中断")
    private String abnormalType;

    @Schema(description = "异常状态", example = "处理中")
    private String abnormalStatus;

    @Schema(description = "异常时间-开始", example = "2025-01-01 00:00:00")
    private String startTime;

    @Schema(description = "异常时间-结束", example = "2025-01-31 23:59:59")
    private String endTime;
}