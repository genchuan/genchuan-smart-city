package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo;

import lombok.Data;

import java.util.List;

/**
 * 评价主体全局概览 VO
 */
@Data
public class EvalSubjectOverviewVO {

    // ========== 1. 卡片数据 ==========
    private CardData cardData;

    // ========== 2. 圆环图数据 ==========
    private List<PieChartItem> typePieChart;    // 主体类型占比（人工/系统）
    private List<PieChartItem> statusPieChart;  // 状态占比（启用/停用/其他）

    // ========== 3. 柱状图数据 ==========
    private List<BarChartItem> memberCountBarChart; // 各主体成员数量对比

    // ========== 内部类定义 ==========

    /**
     * 卡片数据
     */
    @Data
    public static class CardData {
        private Long totalCount;          // 总主体数
        private Long manualSubjectCount;  // 人工主体数
        private Long systemSubjectCount;  // 系统主体数
        private Long enabledSubjectCount; // 启用主体数
    }

    /**
     * 圆环图通用项
     */
    @Data
    public static class PieChartItem {
        private String name;  // 名称（类型名/状态名）
        private Long value;   // 数量
    }

    /**
     * 柱状图通用项（适配成员数量对比）
     */
    @Data
    public static class BarChartItem {
        private String subjectName; // 主体名称（如：专家评审组A）
        private Long memberCount;   // 成员数量
    }
}