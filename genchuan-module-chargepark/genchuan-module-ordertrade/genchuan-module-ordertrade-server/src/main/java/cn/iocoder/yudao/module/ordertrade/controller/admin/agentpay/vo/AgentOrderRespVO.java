package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.AgentOrderPayTypeEnum;
import cn.iocoder.yudao.module.ordertrade.enums.AgentOrderStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 代付订单 Response VO")
@Data
public class AgentOrderRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "订单编号")
    @ExcelProperty("订单编号")
    private String orderNo;

    @Schema(description = "商户ID")
    @ExcelProperty("商户ID")
    private Long merchantId;

    @Schema(description = "商户名称")
    @ExcelProperty("商户名称")
    private String merchantName;

    @Schema(description = "车牌")
    @ExcelProperty("车牌")
    private String carNo;

    @Schema(description = "金额")
    @ExcelProperty("金额")
    private BigDecimal amount;

    @Schema(description = "支付方式")
    @ExcelProperty(value = "支付方式", converter = EnumExcelConverter.class)
    @EnumFormat(AgentOrderPayTypeEnum.class)
    private String payType;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(AgentOrderStatusEnum.class)
    private String status;

    @Schema(description = "支付时间")
    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

    @Schema(description = "关联发票ID")
    @ExcelProperty("关联发票ID")
    private Long invoiceId;

    @Schema(description = "发票编号")
    @ExcelProperty("发票编号")
    private String invoiceNo;

    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;

    @Schema(description = "操作人名称")
    @ExcelProperty("操作人名称")
    private String operatorName;

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
}
