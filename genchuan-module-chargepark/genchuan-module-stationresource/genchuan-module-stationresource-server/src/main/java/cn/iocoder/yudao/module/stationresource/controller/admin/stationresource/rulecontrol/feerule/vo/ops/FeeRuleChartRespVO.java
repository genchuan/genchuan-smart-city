package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FeeRuleChartRespVO {

    /**
     * 各场站规则分布（柱状图）
     */
    private List<StationBarVO> stationBarList;

    /**
     * 卡片统计数据
     */
    private CardDataVO cardData;

    @Data
    public static class StationBarVO {
        /**
         * 场站名称
         */
        private String name;
        /**
         * 规则数量
         */
        private Integer value;
    }

    @Data
    public static class CardDataVO {
        /**
         * 已生效规则数量
         */
        private Integer enableRuleCount;
        /**
         * 总订单匹配率
         */
        private BigDecimal totalMatchRate;
    }
}
