package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分活动 Excel 导出 VO
 */
@Data
public class PointActivityExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("活动名称")
    private String name;

    @ExcelProperty("活动类型")
    private String type;

    @ExcelProperty("开始时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ExcelProperty("结束时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ExcelProperty("积分规则")
    private String rule;

    @ExcelProperty("活动描述")
    private String description;

    @ExcelProperty("适用场站")
    private String stationIds;

    @ExcelProperty("参与人数")
    private Integer joinCount;

    @ExcelProperty("审核人")
    private Long auditorId;

    @ExcelProperty("审核时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    @ExcelProperty("剩余积分额度")
    private Integer remainPoint;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}