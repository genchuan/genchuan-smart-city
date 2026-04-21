package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 学生心理健康看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MentalMgmtStatusDistributionRespVO {

//    mentalStatusDistribution (array): 心理状态分布数据，包含 name、value 字段，用于饼图 / 柱状图渲染。
//    riskLevelDistribution (array): 风险等级分布数据，包含 name、value 字段，用于饼图 / 柱状图渲染。
    @Schema(description = "心理状态分布数据")
    @ExcelProperty("心理状态分布数据")
    private List mentalStatusDistribution;

    @Schema(description = "风险等级分布数据")
    @ExcelProperty("风险等级分布数据")
    private List riskLevelDistribution;
}
