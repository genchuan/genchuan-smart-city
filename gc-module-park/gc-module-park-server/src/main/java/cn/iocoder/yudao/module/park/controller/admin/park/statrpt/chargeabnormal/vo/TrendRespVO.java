package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 收费异常趋势统计 Resp VO")
@Data
public class TrendRespVO {

    @Schema(description = "[异常金额趋势] 按时间维度统计的异常金额变化趋势")
    private List<TrendPointVO> abnormalAmountPointList;

    @Schema(description = "[异常订单数趋势] 按时间维度统计的异常订单数量变化趋势")
    private List<TrendPointVO> abnormalOrderCountPointList;
}
