package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RuleCategoryDetailVO {
    // ====== 规则分类基本信息（来自 eval_rule_category） ======
    private String ruleCategoryId;      // 分类ID（用于前端标识）
    private String ruleCategoryName;    // 分类名称
    private String systemName;         // 所属指标体系名称
    private Integer itemCount;         // 规则项数量
    private String statusName;         // 状态
    private String creatorName;        // 创建人
    private LocalDateTime createTime;  // 创建时间
    private String changeLog;          // 变更日志（完整内容）

    // ====== 关联的规则项列表 ======
    private List<RuleItemDetailVO> ruleItems;

    @Data
    public static class RuleItemDetailVO {
        private String ruleItemName;
        private String indexItemName;
        private String scoreLogic;
        private BigDecimal fullScore;
        private String ruleTypeName;
        // 可根据需要扩展
    }
}