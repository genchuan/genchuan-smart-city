package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.PayAppStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 支付应用 Response VO")
@Data
public class PayAppRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "应用名")
    @ExcelProperty("应用名")
    private String name;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = IntegerEnumExcelConverter.class)
    @IntegerEnumFormat(PayAppStatusEnum.class)
    private Integer status;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
