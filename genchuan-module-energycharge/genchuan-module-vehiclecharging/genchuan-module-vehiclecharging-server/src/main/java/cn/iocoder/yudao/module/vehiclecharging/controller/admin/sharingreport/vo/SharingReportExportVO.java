package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 分账报表导出 VO")
@Data
public class SharingReportExportVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("报表编号")
    private String reportCode;

    @ExcelProperty("报表名称")
    private String reportName;

    @ExcelProperty("报表类型")
    private String reportType;

    @ExcelProperty("时间范围")
    private String timeRange;

    @ExcelProperty("合作方")
    private String cooperator;

    @ExcelProperty("总结算金额(元)")
    private BigDecimal totalSettlementAmount;

    @ExcelProperty("总分账金额(元)")
    private BigDecimal totalSharingAmount;

    @ExcelProperty("结算单数量")
    private Integer billCount;

    @ExcelProperty("创建时间(时间戳)")
    private Long createTime;
}