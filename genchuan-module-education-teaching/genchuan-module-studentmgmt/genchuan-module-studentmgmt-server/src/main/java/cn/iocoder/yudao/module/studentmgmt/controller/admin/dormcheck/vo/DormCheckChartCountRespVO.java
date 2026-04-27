package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 各班级考勤异常人数 / 在寝率统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormCheckChartCountRespVO {

    @Schema(description = "班级名称列表")
    private List<String> labels;
    @Schema(description = "异常人数列表")
    private List<Integer> abnormalCount;
    @Schema(description = "在寝率列表")
    private List<BigDecimal> inRate;

}
