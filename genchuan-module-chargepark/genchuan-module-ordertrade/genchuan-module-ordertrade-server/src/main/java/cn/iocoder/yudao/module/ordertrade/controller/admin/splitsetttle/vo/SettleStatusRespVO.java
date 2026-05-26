package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.SettleStatusStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 结算状态 Response VO")
@Data
public class SettleStatusRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联结算单据ID")
    @ExcelProperty("关联结算单据ID")
    private Long billId;

    @Schema(description = "结算单据编号")
    @ExcelProperty("结算单据编号")
    private String billNo;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(SettleStatusStatusEnum.class)
    private String status;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "异常原因")
    @ExcelProperty("异常原因")
    private String errorReason;

    @Schema(description = "核查人ID")
    @ExcelProperty("核查人ID")
    private Long checkerId;

    @Schema(description = "核查时间")
    @ExcelProperty("核查时间")
    private LocalDateTime checkTime;

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
}
