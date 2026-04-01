package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import lombok.Data;

import java.util.List;

/**
 * 规则分类统计数据 VO
 *
 * @author 亘川智城
 */
@Data
public class RuleCategoryStatisticsVO {

    // ========== 1. 卡片数据 ==========
    private CardData cardData;

    // ========== 2. 圆环图数据 ==========
    private List<PieChartItem> ruleTypePieChart;        // 规则类型占比（加分/扣分）
    private List<PieChartItem> applyObjectTypePieChart; // 适用对象类型占比
    private List<PieChartItem> statusPieChart;          // 状态占比（启用/停用）

    // ========== 3. 柱状图数据 ==========
    private List<BarChartItem> categoryBarChart;        // 各分类规则数量对比

    // ========== 4. 明细列表 ==========
    private List<CategoryStatistics> categoryList;        // 原有分类明细列表

    // ========== 卡片数据 ==========
    @Data
    public static class CardData {
        private Long totalCategoryCount;    // 总分类数
        private Long totalRuleCount;         // 规则总数
        private Long enabledRuleCount;       // 启用规则数
    }

    // ========== 饼图数据 ==========
    @Data
    public static class PieChartItem {
        private String name;  // 名称
        private Long value;  // 数量
    }

    // ========== 柱状图数据 ==========
    @Data
    public static class BarChartItem {
        private String categoryName;   // 分类名称
        private Long ruleCount;        // 规则数量
    }

    @Data
    public static class CategoryStatistics {
        private Long categoryId;
        private String categoryName;
        private String systemName;
        private Long ruleCount;
        private Long indexItemCount;
        private List<RuleStatistics> rules;
    }

    @Data
    public static class RuleStatistics {
        private Long ruleId;
        private String ruleName;
        private Long indexItemCount;
    }
}
