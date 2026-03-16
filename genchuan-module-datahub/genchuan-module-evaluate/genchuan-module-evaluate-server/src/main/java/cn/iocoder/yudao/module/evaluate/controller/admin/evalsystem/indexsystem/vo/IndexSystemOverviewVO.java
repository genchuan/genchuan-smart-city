package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo;

import lombok.Data;

import java.util.List;

/**
 * 指标体系全局概览 VO
 */
@Data
public class IndexSystemOverviewVO {

    // ========== 1. 卡片数据 ==========
    private CardData cardData;

    // ========== 2. 圆环图数据 ==========
    private List<PieChartItem> objectTypePieChart;    // 适用对象类型占比
    private List<PieChartItem> indexTypePieChart;     // 指标类型占比
    private List<PieChartItem> categoryWeightPieChart;// 分类权重分布

    // ========== 3. 基础柱状图数据 ==========
    private List<BarChartItem> systemItemCountBarChart; // 各体系指标项数量对比

    // ========== 内部类定义 ==========

    /**
     * 卡片数据
     */
    @Data
    public static class CardData {
        private Long totalSystemCount;          // 总体系数
        private Long enableSystemCount;         // 启用体系数（假设 status_id=1 为启用）
        private Long totalItemCount;            // 指标项总数（所有体系的itemCount求和）
        private List<VersionCountItem> versionCounts; // 各版本体系数
    }

    /**
     * 各版本体系数（卡片子项）
     */
    @Data
    public static class VersionCountItem {
        private String version;   // 版本号
        private Long count;       // 该版本的体系数量
    }

    /**
     * 圆环图通用项
     */
    @Data
    public static class PieChartItem {
        private String name;  // 名称（适用对象类型名/指标类型名/分类权重名）
        private Long value;   // 数量/权重值
    }

    /**
     * 柱状图通用项
     */
    @Data
    public static class BarChartItem {
        private String systemName; // 体系名称
        private Long itemCount;    // 指标项数量
    }
}
