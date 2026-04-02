package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "管理后台 - 费率设置分布图表 Response VO")
public class RateSettingChartRespVO {

    @Schema(description = "柱状图数据（场站 + 费率数量）")
    private List<RateSettingStationCountRespVO> barData;

    @Schema(description = "统计卡片数据")
    private RateSettingStatusCountRespVO cardData;




}
