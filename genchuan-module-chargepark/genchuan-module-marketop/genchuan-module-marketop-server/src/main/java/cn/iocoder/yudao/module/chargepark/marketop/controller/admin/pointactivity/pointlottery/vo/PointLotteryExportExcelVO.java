package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointLotteryExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("抽奖记录编号")
    private String no;

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty("奖品ID")
    private Long prizeId;

    @ExcelProperty("奖品名称")
    private String prizeName;

    @ExcelProperty("抽奖时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lotteryTime;

    @ExcelProperty("消耗积分")
    private Integer costPoint;

    @ExcelProperty("记录状态")
    private String status;

    @ExcelProperty("发放时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ExcelProperty("发放人")
    private Long senderId;

    @ExcelProperty("发放人名称")
    private String senderName;

    @ExcelProperty("核查结果")
    private String checkResult;

    @ExcelProperty("同步状态")
    private String syncStatus;

    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
