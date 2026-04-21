package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.PayWalletStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 钱包管理 Response VO")
@Data
public class PayWalletRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "用户类型")
    @ExcelProperty("用户类型")
    private Integer userType;

    @Schema(description = "余额（分）")
    @ExcelProperty("余额（分）")
    private Long balance;

    @Schema(description = "冻结余额（分）")
    @ExcelProperty("冻结余额（分）")
    private Long freezeBalance;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = IntegerEnumExcelConverter.class)
    @IntegerEnumFormat(PayWalletStatusEnum.class)
    private Integer status;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
