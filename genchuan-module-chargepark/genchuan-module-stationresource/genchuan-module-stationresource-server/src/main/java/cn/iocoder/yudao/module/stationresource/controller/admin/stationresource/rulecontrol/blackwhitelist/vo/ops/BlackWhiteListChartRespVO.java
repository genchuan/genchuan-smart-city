package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops;

import lombok.Data;
import java.util.List;

@Data
public class BlackWhiteListChartRespVO {

    /**
     * 名单类型占比（饼图）
     */
    private List<TypePieVO> typePieList;

    /**
     * 卡片统计数据
     */
    private CardDataVO cardData;

    @Data
    public static class TypePieVO {
        /**
         * 类型名称
         */
        private String name;
        /**
         * 数量
         */
        private Integer value;
    }

    @Data
    public static class CardDataVO {
        /**
         * 已生效名单数量
         */
        private Integer enableListCount;
        /**
         * 总拦截次数
         */
        private Integer totalInterceptCount;
    }
}
