package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CycleReportExportExcelVO {

    @ExcelProperty("报表主键ID")
    private Long id;

    @ExcelProperty("报表周期")
    private String reportCycle;

    @ExcelProperty("统计时段")
    private String statTime;

    @ExcelProperty("活动数")
    private Integer activityCount;

    @ExcelProperty("参与用户数")
    private Integer joinUserCount;

    @ExcelProperty("抽奖量")
    private Integer lotteryCount;

    @ExcelProperty("中奖率")
    private BigDecimal winningRate;

    @ExcelProperty("优惠券发放量")
    private Integer couponSendCount;

    @ExcelProperty("核销率")
    private BigDecimal couponVerifyRate;

    @ExcelProperty("卡种订单量")
    private Integer cardOrderCount;

    @ExcelProperty("营收")
    private BigDecimal revenue;

    @ExcelProperty("兑换量")
    private Integer exchangeCount;

    @ExcelProperty("总库存")
    private Integer totalStock;

    @ExcelProperty("预警库存数")
    private Integer warnStockCount;

    @ExcelProperty("报表生成状态")
    private String generateStatus;

    @ExcelProperty("报表生成时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime generateTime;

    @ExcelProperty("操作人")
    private String operator;

    @ExcelProperty("生成耗时(ms)")
    private Integer generateCost;

    @ExcelProperty("报表导出次数")
    private Integer exportCount;

    @ExcelProperty("筛选规则")
    private String filterRule;

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
