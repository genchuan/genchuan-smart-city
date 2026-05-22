package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CouponMgmtExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("券名称")
    private String name;

    @ExcelProperty("券类型")
    private String type;

    @ExcelProperty("面额")
    private BigDecimal amount;

    @ExcelProperty("使用条件")
    private String useCondition;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("发放人")
    private Long senderId;

    @ExcelProperty("发放人名称")
    private String senderName;

    @ExcelProperty("发放时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ExcelProperty("领取人")
    private Long receiverId;

    @ExcelProperty("领取人名称")
    private String receiverName;

    @ExcelProperty("核销时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verifyTime;

    @ExcelProperty("有效期")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validTime;

    @ExcelProperty("券描述")
    private String description;

    @ExcelProperty("适用场站")
    private String stationIds;

    @ExcelProperty("适用场站名称")
    private String stationNames;

    @ExcelProperty("领取量")
    private Integer sendCount;

    @ExcelProperty("核销率")
    private BigDecimal verifyRate;

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
