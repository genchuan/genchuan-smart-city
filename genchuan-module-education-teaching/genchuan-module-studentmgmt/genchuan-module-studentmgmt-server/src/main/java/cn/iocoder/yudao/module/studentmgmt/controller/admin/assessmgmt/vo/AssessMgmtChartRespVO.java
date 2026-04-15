package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


@Schema(description = "管理后台 - 考评管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssessMgmtChartRespVO {

//    totalCount (integer): 本期考评总记录数。
//    avgScore (decimal): 本期班级平均得分。
//    topRankClass (string): 本期排名第一的班级。
//    assessTypeCount (object): 各考评类型的记录数统计，key 为考评类型编码，value 为数量。
//    statusCount (object): 各状态的记录数统计，key 为状态编码，value 为数量。
//

    @Schema(description = "本期考评总记录数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("本期考评总记录数")
    private Integer totalCount;
    @Schema(description = "本期班级平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("本期班级平均得分")
    private BigDecimal avgScore;
    @Schema(description = "本期排名第一的班级", requiredMode = Schema.RequiredMode.REQUIRED, example = "1班")
    @ExcelProperty("本期排名第一的班级")
    private String topRankClass;
    @Schema(description = "各考评类型的记录数统计", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("各考评类型的记录数统计")
    private Object assessTypeCount;
    @Schema(description = "各状态的记录数统计", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("各状态的记录数统计")
    private Object statusCount;

//
//    @Schema(description = "考评记录总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @ExcelProperty("考评记录总数")
//    private Integer totalAssessCount;
//
//    @Schema(description = "待发布考评数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @ExcelProperty("待发布考评数")
//    private Integer pendingPublishCount;
//
//    @Schema(description = "今日发布数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @ExcelProperty("今日发布数")
//    private Integer todayPublishCount;
//
//    @Schema(description = "卫生平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @ExcelProperty("卫生平均得分")
//    private BigDecimal hygieneScore;
//
//    @Schema(description = "早操平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @ExcelProperty("早操平均得分")
//    private BigDecimal morningExerciseScore;
//
//    @Schema(description = "文明班级平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @ExcelProperty("文明班级平均得分")
//    private BigDecimal civilizedScore;
//
//    @Schema(description = "黑板报平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @ExcelProperty("黑板报平均得分")
//    private BigDecimal blackboardScore;

}
