package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockControlExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("卡种ID")
    private Long cardId;

    @ExcelProperty("卡种名称")
    private String cardName;

    @ExcelProperty("当前库存")
    private Integer currentStock;

    @ExcelProperty("预警阈值")
    private Integer warnThreshold;

    @ExcelProperty("库存状态")
    private String status;

    @ExcelProperty("告警状态")
    private String warnStatus;

    @ExcelProperty("同步时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime syncTime;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建者名称")
    private String creatorName;

    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
