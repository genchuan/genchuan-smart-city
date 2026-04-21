package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ChargeParkLinkChartRespVO {

    /**
     * 优惠使用趋势（折线图）
     */
    private List<DiscountLineVO> discountLineList;

    /**
     * 各场站订单量（柱状图）
     */
    private List<OrderBarVO> orderBarList;

    /**
     * 卡片统计
     */
    private CardDataVO cardData;

    @Data
    public static class DiscountLineVO {
        private String date;      // 日期：2025-03
        private Integer useCount; // 使用次数
    }

    @Data
    public static class OrderBarVO {
        private String name;     // 场站名称
        private Integer value;  // 订单量
    }

    @Data
    public static class CardDataVO {
        private Integer todayOrderCount; // 今日订单量
        private BigDecimal todayIncome;  // 今日营收
        private BigDecimal payRate;      // 支付率
    }
}
