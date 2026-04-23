package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 德育评比排名看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompareMgmtScoreRankRespVO {

    @Schema(description = "班级名称列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\n" +
            "高一 (1) 班" +
            "高一 (3) 班" +
            "高一 (2) 班" +
            "]")
    private List classList;

    @Schema(description = "对应班级的得分列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\n" +
            "92.5" +
            "90.0" +
            "88.0" +
            "]")
    private List<BigDecimal> scoreList;

    @Schema(description = "对应班级的排名列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\n" +
            "1" +
            "2" +
            "3" +
            "]")
    private List<Integer> rankList;

}
