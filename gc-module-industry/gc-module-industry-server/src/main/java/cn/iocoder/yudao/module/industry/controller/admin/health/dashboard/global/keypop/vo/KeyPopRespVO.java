package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 重点人群健康视图 Response VO")
@Data
public class KeyPopRespVO {

    // =============================
    // 左侧卡片数据 - 重点人群分类数量
    // =============================

    @Schema(description = "老年人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("老年人")
    private Integer elderlyCount;

    @Schema(description = "慢性病患者人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "80")
    @ExcelProperty("慢性病患者")
    private Integer chronicPatientCount;

    @Schema(description = "孕产妇人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @ExcelProperty("孕产妇")
    private Integer maternalCount;

    @Schema(description = "高风险人群数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
    @ExcelProperty("高风险人群")
    private Integer highRiskCount;

    @Schema(description = "重点人群健康随访率（百分比）", requiredMode = Schema.RequiredMode.REQUIRED, example = "92.5")
    @ExcelProperty("随访率")
    private BigDecimal keyPopFollowRate;

    // =============================
    // 中间地图数据 - 各社区重点人群分布
    // =============================
    @Schema(description = "各社区重点人群分布列表")
    private List<CommunityKeyPop> communityKeyPopList;

    // =============================
    // 社区重点人群分布对象
    // =============================
    @Data
    @Schema(description = "社区重点人群分布")
    public static class CommunityKeyPop {

        @Schema(description = "社区名称", example = "朝阳社区")
        private String communityName;

        @Schema(description = "老年人数", example = "50")
        private Integer elderlyCount;

        @Schema(description = "慢性病患者人数", example = "30")
        private Integer chronicPatientCount;

        @Schema(description = "孕产妇人数", example = "20")
        private Integer maternalCount;

        @Schema(description = "高风险人数", example = "10")
        private Integer highRiskCount;

        @Schema(description = "社区更新时间，格式 yyyy-MM-dd HH:mm:ss", example = "2025-12-01 12:00:00")
        private String updateTime;
    }

    // =============================
    // 右侧高风险人群列表
    // =============================
    @Schema(description = "高风险人群列表")
    private List<HighRiskPerson> highRiskPersonList;

    @Data
    @Schema(description = "高风险人群信息")
    public static class HighRiskPerson {

        @Schema(description = "姓名", example = "张三")
        private String name;

        @Schema(description = "年龄", example = "65")
        private Integer age;

        @Schema(description = "风险类型", example = "老年人高血压")
        private String riskType;

        @Schema(description = "所在社区", example = "朝阳社区")
        private String communityName;

    }
}
