package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReceiveRecordExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("记录编号")
    private String no;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("优惠券ID")
    private Long couponId;

    @ExcelProperty("优惠券名称")
    private String couponName;

    @ExcelProperty("领取时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("核销时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verifyTime;

    @ExcelProperty("核查结果")
    private String checkResult;

    @ExcelProperty("同步状态")
    private String syncStatus;

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
