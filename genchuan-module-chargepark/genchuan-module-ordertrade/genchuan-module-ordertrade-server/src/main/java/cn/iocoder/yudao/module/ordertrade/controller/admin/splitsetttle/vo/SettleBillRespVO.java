package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.SettleBillStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结算单据 Response VO")
@Data
public class SettleBillRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "单据编号")
    @ExcelProperty("单据编号")
    private String billNo;

    @Schema(description = "合作方ID")
    @ExcelProperty("合作方ID")
    private Long partnerId;

    @Schema(description = "合作方名称")
    @ExcelProperty("合作方名称")
    private String partnerName;

    @Schema(description = "收费总额")
    @ExcelProperty("收费总额")
    private BigDecimal totalAmount;

    @Schema(description = "分成金额")
    @ExcelProperty("分成金额")
    private BigDecimal splitAmount;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(SettleBillStatusEnum.class)
    private String status;

    @Schema(description = "审核人ID")
    @ExcelProperty("审核人ID")
    private Long auditorId;

    @Schema(description = "审核人名称")
    @ExcelProperty("审核人名称")
    private String auditorName;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "结算时间")
    @ExcelProperty("结算时间")
    private LocalDateTime settleTime;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新者")
    private String updater;

}
