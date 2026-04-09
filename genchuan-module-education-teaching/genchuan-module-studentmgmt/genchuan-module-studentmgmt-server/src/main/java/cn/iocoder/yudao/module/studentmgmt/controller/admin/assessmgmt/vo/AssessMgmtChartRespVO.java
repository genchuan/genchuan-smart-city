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

    @Schema(description = "考评记录总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("考评记录总数")
    private Integer totalAssessCount;

    @Schema(description = "待发布考评数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("待发布考评数")
    private Integer pendingPublishCount;

    @Schema(description = "今日发布数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("今日发布数")
    private Integer todayPublishCount;

    @Schema(description = "卫生平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("卫生平均得分")
    private BigDecimal hygieneScore;

    @Schema(description = "早操平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("早操平均得分")
    private BigDecimal morningExerciseScore;

    @Schema(description = "文明班级平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("文明班级平均得分")
    private BigDecimal civilizedScore;

    @Schema(description = "黑板报平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("黑板报平均得分")
    private BigDecimal blackboardScore;

}
