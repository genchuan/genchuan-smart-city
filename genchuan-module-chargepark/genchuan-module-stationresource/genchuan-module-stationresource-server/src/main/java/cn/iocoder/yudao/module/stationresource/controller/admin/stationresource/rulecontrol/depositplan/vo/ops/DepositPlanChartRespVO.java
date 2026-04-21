package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops;

import lombok.Data;
import java.util.List;

@Data
public class DepositPlanChartRespVO {

    /**
     * 各场景方案订单分布（柱状图）
     */
    private List<SceneBarVO> sceneBarList;

    /**
     * 卡片统计数据
     */
    private CardDataVO cardData;

    @Data
    public static class SceneBarVO {
        /**
         * 场景名称
         */
        private String name;
        /**
         * 订单总量
         */
        private Integer value;
    }

    @Data
    public static class CardDataVO {
        /**
         * 已生效方案数量
         */
        private Integer enablePlanCount;
        /**
         * 总押金订单量
         */
        private Integer totalDepositOrderCount;
    }
}
