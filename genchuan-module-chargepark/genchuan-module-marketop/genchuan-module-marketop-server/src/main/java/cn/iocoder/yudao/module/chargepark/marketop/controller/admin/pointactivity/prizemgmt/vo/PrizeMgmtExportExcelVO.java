package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrizeMgmtExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("奖品名称")
    private String name;

    @ExcelProperty("奖品类型")
    private String type;

    @ExcelProperty("当前库存")
    private Integer stock;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("绑定活动ID")
    private Long activityId;

    @ExcelProperty("绑定活动名称")
    private String activityName;

    @ExcelProperty("发放量")
    private Integer sendCount;

    @ExcelProperty("同步时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime syncTime;

    @ExcelProperty("预警阈值")
    private Integer warnThreshold;

    @ExcelProperty("奖品描述")
    private String description;

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
