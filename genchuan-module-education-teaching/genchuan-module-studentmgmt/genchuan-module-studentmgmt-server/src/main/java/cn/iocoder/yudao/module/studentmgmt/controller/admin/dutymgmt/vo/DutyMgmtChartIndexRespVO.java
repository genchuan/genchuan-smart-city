package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 值班核心指标统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DutyMgmtChartIndexRespVO {

//    monthList (array): 月份列表。
//    dutyCountList (array): 对应月份值班次数统计。
//    checkInRateList (array): 对应月份打卡率统计。
//    shiftRateList (array): 对应月份调班率统计。
//    vehicleRateList (array): 对应月份出车率统计。
    @Schema(description = "月份列表")
    @ExcelProperty("月份列表")
    private List<String> monthList;
    @Schema(description = "值班次数列表")
    @ExcelProperty("值班次数列表")
    private List<Integer> dutyCountList;
    @Schema(description = "打卡率列表")
    @ExcelProperty("打卡率列表")
    private List<BigDecimal> checkInRateList;
    @Schema(description = "调班率列表")
    @ExcelProperty("调班率列表")
    private List<BigDecimal> shiftRateList;
    @Schema(description = "出车率列表")
    @ExcelProperty("出车率列表")
    private List<BigDecimal> vehicleRateList;


}
