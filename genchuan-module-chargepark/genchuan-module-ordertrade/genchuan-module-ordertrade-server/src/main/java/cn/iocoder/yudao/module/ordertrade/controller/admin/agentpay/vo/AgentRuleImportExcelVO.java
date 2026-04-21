package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 代付规则导入 Excel VO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class AgentRuleImportExcelVO {

    @ExcelProperty("主键ID（更新时填写）")
    private Long id;

    @ExcelProperty("规则名称")
    private String name;

    @ExcelProperty("商户ID")
    private Long merchantId;

    @ExcelProperty("代付类型(merchant/enterprise/public)")
    private String agentType;

    @ExcelProperty("单次限额")
    private BigDecimal singleLimit;

    @ExcelProperty("日累计限额")
    private BigDecimal dayLimit;

    @ExcelProperty("适用场景")
    private String scene;

    @ExcelProperty("状态(pending/enabled/disabled)")
    private String status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("备用字段1")
    private String reserve1;

    @ExcelProperty("备用字段2")
    private String reserve2;
}
