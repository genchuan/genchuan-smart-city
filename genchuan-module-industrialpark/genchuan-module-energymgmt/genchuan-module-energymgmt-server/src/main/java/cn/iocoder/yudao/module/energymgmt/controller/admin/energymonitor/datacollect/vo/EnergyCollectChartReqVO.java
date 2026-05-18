package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 能耗采集表图表数据 Request VO")
@Data
public class EnergyCollectChartReqVO {

    @Schema(description = "时间范围")
    private String timeRange;

}
