package cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo;

import lombok.Data;

import java.util.List;

/**
 * 标准分类统计数据 VO
 *
 * @author 亘川智城
 */
@Data
public class StandardCategoryStatisticsVO {

    // ========== 1. 卡片数据 ==========
    private CardData cardData;

    // ========== 2. 圆环图数据 ==========
    private List<PieChartItem> statusPieChart;       // 状态占比（启用/停用）
    private List<PieChartItem> systemPieChart;        // 指标体系占比
    private List<PieChartItem> gradePieChart;        // 等级分布占比

    // ========== 3. 柱状图数据 ==========
    private List<BarChartItem> categoryBarChart;      // 各分类标准项数量对比

    // ========== 4. 明细列表 ==========
    private List<CategoryStatistics> categoryList;    // 分类明细列表

    // ========== 卡片数据 ==========
    @Data
    public static class CardData {
        private Long totalCategoryCount;    // 总分类数
        private Long totalItemCount;         // 标准项总数
        private Long enabledCategoryCount;   // 启用分类数
    }

    // ========== 饼图数据 ==========
    @Data
    public static class PieChartItem {
        private String name;  // 名称
        private Long value;   // 数量
    }

    // ========== 柱状图数据 ==========
    @Data
    public static class BarChartItem {
        private String categoryName;   // 分类名称
        private Long itemCount;        // 标准项数量
    }

    // ========== 分类明细 ==========
    @Data
    public static class CategoryStatistics {
        private Long categoryId;
        private String categoryName;
        private String systemName;
        private String statusName;
        private Long itemCount;
        private List<ItemStatistics> items;
    }

    // ========== 标准项明细 ==========
    @Data
    public static class ItemStatistics {
        private Long itemId;
        private String grade;
        private String scoreRange;
    }
}
