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

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "车牌号码")
    @ExcelProperty("车牌号码")
    private String carNo;

    @Schema(description = "欠费金额")
    @ExcelProperty("欠费金额")
    private BigDecimal debtAmount;

    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "追缴完成时间")
    @ExcelProperty("追缴完成时间")
    private LocalDateTime collectTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

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
