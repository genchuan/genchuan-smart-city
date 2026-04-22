package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 服务运营报表导出行:扁平化 {@link ServiceOpReportRespVO},把 同比Data/环比Data 拆成独立列。
 * 只用于 Excel/PDF 导出,不用于 HTTP 响应。
 */
@Data
@ExcelIgnoreUnannotated
public class ServiceOpReportExportRow {

    @ExcelProperty("报表 ID")
    private String id;

    @ExcelProperty("报表类型")
    private String reportType;

    @ExcelProperty("时间尺度")
    private String timeScale;

    @ExcelProperty("统计周期")
    private String statPeriod;

    @ExcelProperty("生成时间")
    private String createTime;

    @ExcelProperty("救援完成率")
    private String rescueFinishRate;

    @ExcelProperty("预约成功率")
    private String reserveSuccessRate;

    @ExcelProperty("投诉处理率")
    private String complaintHandleRate;

    @ExcelProperty("同比-救援完成率")
    private String yoyRescueFinishRate;

    @ExcelProperty("同比-预约成功率")
    private String yoyReserveSuccessRate;

    @ExcelProperty("环比-救援完成率")
    private String qoqRescueFinishRate;

    @ExcelProperty("环比-预约成功率")
    private String qoqReserveSuccessRate;

    @ExcelProperty("创建者")
    private String creator;

}
