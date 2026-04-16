package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 逃费记录 Response VO")
@Data
public class DebtRecordRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "记录编号")
    @ExcelProperty("记录编号")
    private String recordNo;
    @Schema(description = "车牌")
    @ExcelProperty("车牌")
    private String plateNo;
    @Schema(description = "欠费订单数")
    @ExcelProperty("欠费订单数")
    private Integer arrearOrderCount;
    @Schema(description = "欠费金额")
    @ExcelProperty("欠费金额")
    private BigDecimal arrearAmount;
    @Schema(description = "追缴状态")
    @ExcelProperty("追缴状态")
    private String status;
    @Schema(description = "所属场站ID")
    @ExcelProperty("所属场站ID")
    private Long stationId;
    @Schema(description = "追缴进度")
    @ExcelProperty("追缴进度")
    private String collectProgress;
    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
