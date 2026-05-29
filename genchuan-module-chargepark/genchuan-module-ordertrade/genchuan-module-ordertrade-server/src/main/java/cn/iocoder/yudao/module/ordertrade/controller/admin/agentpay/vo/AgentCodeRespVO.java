package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.AgentCodeStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 代付码 Response VO")
@Data
public class AgentCodeRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "代付码")
    @ExcelProperty("代付码")
    private String code;

    @Schema(description = "商户ID")
    @ExcelProperty("商户ID")
    private Long merchantId;

    @Schema(description = "商户名称")
    @ExcelProperty("商户名称")
    private String merchantName;

    @Schema(description = "关联规则ID")
    @ExcelProperty("关联规则ID")
    private Long ruleId;

    @Schema(description = "规则名称")
    @ExcelProperty("规则名称")
    private String ruleName;

    @Schema(description = "过期时间")
    @ExcelProperty("过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(AgentCodeStatusEnum.class)
    private String status;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "用户名称")
    @ExcelProperty("用户名称")
    private String userName;

    @Schema(description = "使用时间")
    @ExcelProperty("使用时间")
    private LocalDateTime useTime;

    @Schema(description = "关联订单ID")
    @ExcelProperty("关联订单ID")
    private Long orderId;

    @Schema(description = "关联订单编号")
    @ExcelProperty("关联订单编号")
    private String orderNo;

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
