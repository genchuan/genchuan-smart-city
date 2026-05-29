package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.AgentRecordStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 代付记录 Response VO")
@Data
public class AgentRecordRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "记录编号")
    @ExcelProperty("记录编号")
    private String recordNo;

    @Schema(description = "关联订单ID")
    @ExcelProperty("关联订单ID")
    private Long orderId;

    @Schema(description = "关联订单编号")
    @ExcelProperty("关联订单编号")
    private String orderNo;

    @Schema(description = "商户ID")
    @ExcelProperty("商户ID")
    private Long merchantId;

    @Schema(description = "商户名称")
    @ExcelProperty("商户名称")
    private String merchantName;

    @Schema(description = "变动金额")
    @ExcelProperty("变动金额")
    private BigDecimal amount;

    @Schema(description = "交易时间")
    @ExcelProperty("交易时间")
    private LocalDateTime tradeTime;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(AgentRecordStatusEnum.class)
    private String status;

    @Schema(description = "核查人ID")
    @ExcelProperty("核查人ID")
    private Long checkerId;

    @Schema(description = "核查人名称")
    @ExcelProperty("核查人名称")
    private String checkerName;

    @Schema(description = "核查时间")
    @ExcelProperty("核查时间")
    private LocalDateTime checkTime;

    @Schema(description = "核查结果")
    @ExcelProperty("核查结果")
    private String checkResult;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    /*@Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;*/

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}
