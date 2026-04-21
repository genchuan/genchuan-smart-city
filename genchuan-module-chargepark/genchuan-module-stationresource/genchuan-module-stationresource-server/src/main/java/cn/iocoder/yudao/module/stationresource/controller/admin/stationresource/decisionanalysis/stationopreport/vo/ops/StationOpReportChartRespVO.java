package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class StationOpReportChartRespVO {

    /**
     * 运营趋势折线图数据
     */
    private List<OperateLineVO> operateLineList;

    /**
     * 片区营收柱状图数据
     */
    private List<AreaBarVO> areaBarList;

    /**
     * 顶部卡片统计
     */
    private CardDataVO cardData;

    @Data
    public static class OperateLineVO {
        private String date;        // 日期
        private Integer orderCount; // 订单量
        private BigDecimal income;  // 营收
    }

    @Data
    public static class AreaBarVO {
        private String name;    // 片区名称
        private BigDecimal income; // 营收
    }

    @Data
    public static class CardDataVO {
        private Integer totalOrderCount;    // 总订单量
        private BigDecimal totalIncome;     // 总营收
        private BigDecimal totalSpaceUseRate; // 总车位使用率
    }
}
