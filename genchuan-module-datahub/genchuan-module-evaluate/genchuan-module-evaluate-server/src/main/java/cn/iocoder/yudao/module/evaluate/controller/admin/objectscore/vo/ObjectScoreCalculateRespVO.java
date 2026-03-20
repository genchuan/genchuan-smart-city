package cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Schema(description = "管理后台 - 计算公司得分 Response VO")
@Data
@Builder
public class ObjectScoreCalculateRespVO {

    @Schema(description = "公司得分记录ID（eval_object_score表主键）", example = "100")
    private Long objectScoreId;

    @Schema(description = "对象ID (关联eval_object.id)", example = "20118")
    private Long objectId;

    @Schema(description = "对象名称", example = "深圳市某某公司")
    private String objectName;

    @Schema(description = "体系ID (关联eval_index_system.id)", example = "7259")
    private Long systemId;

    @Schema(description = "体系名称", example = "卫生评价体系")
    private String systemName;

    @Schema(description = "计算后的总得分", example = "85.50")
    private BigDecimal totalScore;

    @Schema(description = "得分明细列表")
    private List<ScoreDetail> details;

    @Schema(description = "得分明细")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScoreDetail {

        @Schema(description = "统计记录ID", example = "100")
        private Long statisticId;

        @Schema(description = "指标项ID", example = "200")
        private Long itemId;

        @Schema(description = "指标项名称", example = "垃圾分类")
        private String itemName;

        @Schema(description = "指标分类ID", example = "cat_001")
        private String categoryId;

        @Schema(description = "指标分类名称", example = "环保类")
        private String categoryName;

        @Schema(description = "指标项权重", example = "0.30")
        private BigDecimal itemWeight;

        @Schema(description = "指标分类权重", example = "0.50")
        private BigDecimal categoryWeight;

        @Schema(description = "统计得分（规则回填分）", example = "60")
        private Long score;

        @Schema(description = "加权得分（score × itemWeight × categoryWeight）", example = "9.00")
        private BigDecimal weightedScore;
    }
}
