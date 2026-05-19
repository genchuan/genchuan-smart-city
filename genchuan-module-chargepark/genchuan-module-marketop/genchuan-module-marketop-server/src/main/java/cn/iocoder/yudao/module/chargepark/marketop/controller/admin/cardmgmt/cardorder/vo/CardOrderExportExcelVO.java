package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CardOrderExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("订单编号")
    private String no;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("卡种ID")
    private Long cardId;

    @ExcelProperty("卡种名称")
    private String cardName;

    @ExcelProperty("订单金额")
    private BigDecimal amount;

    @ExcelProperty("支付状态")
    private String payStatus;

    @ExcelProperty("支付时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    @ExcelProperty("激活时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeTime;

    @ExcelProperty("开票状态")
    private String invoiceStatus;

    @ExcelProperty("归档时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime archiveTime;

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
