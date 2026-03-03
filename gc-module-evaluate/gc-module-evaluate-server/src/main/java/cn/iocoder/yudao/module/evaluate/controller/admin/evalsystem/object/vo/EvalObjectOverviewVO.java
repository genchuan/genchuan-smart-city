package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import lombok.Data;
import java.util.List;

/**
 * 评价对象全局概览 VO
 */
@Data
public class EvalObjectOverviewVO {

    // ========== 1. 卡片数据 ==========
    private CardData cardData;

    // ========== 2. 圆环图数据 ==========
    private List<PieChartItem> typePieChart;    // 对象类型占比
    private List<PieChartItem> areaPieChart;    // 所属区域占比
    private List<PieChartItem> statusPieChart;  // 状态占比

    // ========== 3. 柱状图数据 ==========
    private List<BarChartItem> areaBarChart;    // 不同区域对象数量对比

    // ========== 内部类定义 ==========

    /**
     * 卡片数据
     */
    @Data
    public static class CardData {
        private Long totalCount;          // 总对象数
        private Long normalStatusCount;   // 正常状态对象数（假设 status_id=1）
        private Long pendingCheckCount;   // 待校验对象数（假设 status_id=2）
        private List<TypeCountItem> typeCounts; // 各类型对象数
    }

    /**
     * 各类型对象数（卡片子项）
     */
    @Data
    public static class TypeCountItem {
        private String typeId;   // 类型ID
        private String typeName; // 类型名称（关联表后填充）
        private Long count;      // 数量
    }

    /**
     * 圆环图通用项
     */
    @Data
    public static class PieChartItem {
        private String name;  // 名称（类型名/区域名/状态名）
        private Long value;   // 数量
    }

    /**
     * 柱状图通用项
     */
    @Data
    public static class BarChartItem {
        private String areaName; // 区域名称
        private Long count;      // 数量
    }
}
