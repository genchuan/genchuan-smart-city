package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops;

import lombok.Data;
import java.util.List;

@Data
public class OfftimeRuleChartRespVO {

    /**
     * 错时订单趋势（折线图）
     */
    private List<OrderLineVO> orderLineList;

    /**
     * 卡片统计数据
     */
    private CardDataVO cardData;

    @Data
    public static class OrderLineVO {
        /**
         * 日期（月份）
         */
        private String date;
        /**
         * 订单量
         */
        private Integer orderCount;
    }

    @Data
    public static class CardDataVO {
        /**
         * 已生效规则数量
         */
        private Integer enableRuleCount;
        /**
         * 总错时订单量
         */
        private Integer totalOffOrderCount;
    }
}
