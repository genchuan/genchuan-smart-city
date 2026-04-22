package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 周期报表 Excel 导出行(扁平化,列对齐客户 07 文档展示字段)
 */
@Data
@ExcelIgnoreUnannotated
public class CycleReportExportRow {

    @ExcelProperty("报表ID")
    private String id;

    @ExcelProperty("报表周期")
    private String reportCycle;

    @ExcelProperty("统计时段")
    private String statTime;

    @ExcelProperty("救援完成率")
    private String rescueCompleteRate;

    @ExcelProperty("预约成功率")
    private String reserveSuccessRate;

    @ExcelProperty("投诉处理率")
    private String complaintHandleRate;

    @ExcelProperty("寻车定位成功率")
    private String findCarSuccessRate;

    @ExcelProperty("空位推送成功率")
    private String spacePushSuccessRate;

    @ExcelProperty("生效话术数")
    private String effectiveWordingCount;

    @ExcelProperty("救援总量")
    private String rescueTotal;

    @ExcelProperty("预约总量")
    private String reserveTotal;

    @ExcelProperty("投诉总量")
    private String complaintTotal;

    @ExcelProperty("空位推送总量")
    private String spacePushTotal;

    @ExcelProperty("生成状态")
    private String generateStatus;

    @ExcelProperty("报表生成时间")
    private String generateTime;

    @ExcelProperty("操作人")
    private String operator;

    @ExcelProperty("同比增长率")
    private String yearOnYearGrowthRate;

    @ExcelProperty("环比增长率")
    private String monthOnMonthGrowthRate;

    @ExcelProperty("服务状态占比")
    private String serviceStatusRatio;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private String createTime;

}
