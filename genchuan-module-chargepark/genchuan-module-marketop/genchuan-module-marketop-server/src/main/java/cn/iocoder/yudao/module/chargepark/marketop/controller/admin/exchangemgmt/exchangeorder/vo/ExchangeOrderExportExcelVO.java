package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExchangeOrderExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("订单编号")
    private String no;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("类目ID")
    private Long categoryId;

    @ExcelProperty("类目名称")
    private String categoryName;

    @ExcelProperty("商品ID")
    private Long goodsId;

    @ExcelProperty("商品名称")
    private String goodsName;

    @ExcelProperty("消耗积分")
    private Integer costPoint;

    @ExcelProperty("支付状态")
    private String payStatus;

    @ExcelProperty("支付时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    @ExcelProperty("发货时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shipTime;

    @ExcelProperty("物流信息")
    private String logisticsInfo;

    @ExcelProperty("归档时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime archiveTime;

    @ExcelProperty("备用字段1")
    private String reserve1;

    @ExcelProperty("备用字段2")
    private String reserve2;

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
