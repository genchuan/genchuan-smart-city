package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.PayCallbackStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 回调通知 Response VO")
@Data
public class PayCallbackRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "应用ID")
    @ExcelProperty("应用ID")
    private Long appId;

    @Schema(description = "类型")
    @ExcelProperty("类型")
    private Integer type;

    @Schema(description = "商户订单号")
    @ExcelProperty("商户订单号")
    private String merchantOrderId;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = IntegerEnumExcelConverter.class)
    @IntegerEnumFormat(PayCallbackStatusEnum.class)
    private Integer status;

    @Schema(description = "下次通知时间")
    @ExcelProperty("下次通知时间")
    private LocalDateTime nextNotifyTime;

    @Schema(description = "通知次数")
    @ExcelProperty("通知次数")
    private Integer notifyTimes;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
