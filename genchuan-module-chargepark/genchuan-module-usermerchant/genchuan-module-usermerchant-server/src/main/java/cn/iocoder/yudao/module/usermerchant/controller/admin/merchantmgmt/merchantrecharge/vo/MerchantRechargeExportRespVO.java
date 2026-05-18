package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 商户充值 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MerchantRechargeExportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2272")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "商户名称")
    @ExcelProperty("商户名称")
    private String merchantName;

    @Schema(description = "充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充值金额")
    private BigDecimal amount;

    @Schema(description = "支付渠道", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("支付渠道")
    private String payChannel;

    @Schema(description = "充值状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("充值状态")
    private String status;

    @Schema(description = "充值订单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充值订单号")
    private String orderNo;

    @Schema(description = "支付时间")
    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

    @Schema(description = "确认时间")
    @ExcelProperty("确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}