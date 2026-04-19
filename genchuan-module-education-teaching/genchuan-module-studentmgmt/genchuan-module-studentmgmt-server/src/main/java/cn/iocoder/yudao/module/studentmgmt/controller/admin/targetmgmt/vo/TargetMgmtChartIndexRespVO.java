package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 指标管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TargetMgmtChartIndexRespVO {

    @Schema(description = "总指标数量", requiredMode = Schema.RequiredMode.REQUIRED, example = " 2")
    @ExcelProperty("总指标数量")
    private Integer totalTargetCount;
    @Schema(description = "已启用指标数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
    @ExcelProperty("已启用指标数量")
    private Integer enabledTargetCount;
    @Schema(description = "触发预警的指标数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @ExcelProperty("触发预警的指标数量")
    private Integer warnTargetCount;
    @Schema(description = "指标平均得分", requiredMode = Schema.RequiredMode.REQUIRED, example = "4.5")
    @ExcelProperty("指标平均得分")
    private BigDecimal avgScore;


}
