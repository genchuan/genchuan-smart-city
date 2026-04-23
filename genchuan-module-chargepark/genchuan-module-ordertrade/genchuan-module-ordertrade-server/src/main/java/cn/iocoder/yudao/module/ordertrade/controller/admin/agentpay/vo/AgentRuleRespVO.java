package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.AgentRuleAgentTypeEnum;
import cn.iocoder.yudao.module.ordertrade.enums.AgentRuleStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 代付规则 Response VO")
@Data
public class AgentRuleRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则名称")
    @ExcelProperty("规则名称")
    private String name;

    @Schema(description = "商户ID")
    @ExcelProperty("商户ID")
    private Long merchantId;

    @Schema(description = "商户名称")
    @ExcelProperty("商户名称")
    private String merchantName;

    @Schema(description = "代付类型")
    @ExcelProperty(value = "代付类型", converter = EnumExcelConverter.class)
    @EnumFormat(AgentRuleAgentTypeEnum.class)
    private String agentType;

    @Schema(description = "单次限额")
    @ExcelProperty("单次限额")
    private BigDecimal singleLimit;

    @Schema(description = "日累计限额")
    @ExcelProperty("日累计限额")
    private BigDecimal dayLimit;

    @Schema(description = "适用场景")
    @ExcelProperty("适用场景")
    private String scene;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(AgentRuleStatusEnum.class)
    private String status;

    @Schema(description = "使用次数")
    @ExcelProperty("使用次数")
    private Integer useCount;

    @Schema(description = "审核人ID")
    @ExcelProperty("审核人ID")
    private Long auditorId;

    @Schema(description = "审核人名称")
    @ExcelProperty("审核人名称")
    private String auditorName;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "最后更新时间")
    @ExcelProperty("最后更新时间")
    private LocalDateTime lastUpdateTime;

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
