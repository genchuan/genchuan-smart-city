package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.caseview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 执法案件总览 Response VO")
@Data
public class LawCaseViewRespVO {

    @Schema(description = "案件类型分布列表")
    private List<CaseTypeCount> typeDistribution;

    @Schema(description = "案件来源分布列表")
    private List<CaseSourceCount> sourceDistribution;

    @Schema(description = "案件办理进度分布列表")
    private List<ProgressStageCount> progressDistribution;

    @Schema(description = "超期未办结案件数", example = "18")
    @ExcelProperty("超期未办结案件数")
    private Integer overdueCaseCount;


    // ==================== 内部类结构 ====================

    @Data
    @Schema(description = "案件类型统计")
    public static class CaseTypeCount {

        @Schema(description = "案件类型", example = "违法建设")
        @ExcelProperty("案件类型")
        private String caseType;

        @Schema(description = "该类型案件数量", example = "35")
        @ExcelProperty("数量")
        private Integer caseCount;
    }


    @Data
    @Schema(description = "案件来源统计")
    public static class CaseSourceCount {

        @Schema(description = "案件来源", example = "热线举报")
        @ExcelProperty("案件来源")
        private String caseSource;

        @Schema(description = "该来源案件数量", example = "22")
        @ExcelProperty("数量")
        private Integer caseCount;
    }


    @Data
    @Schema(description = "案件办理进度统计")
    public static class ProgressStageCount {

        @Schema(description = "办理进度阶段", example = "调查取证")
        @ExcelProperty("办理进度")
        private String progressStage;

        @Schema(description = "该阶段案件数量", example = "18")
        @ExcelProperty("数量")
        private Integer caseCount;
    }

}
