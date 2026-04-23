package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 商户信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MerchantInfoPageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3360")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "商户名称，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("商户名称，唯一")
    private String name;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系人")
    private String contact;

    @Schema(description = "联系手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系手机号")
    private String phone;

    @Schema(description = "商户类型：充电商户/停车商户/充停一体商户", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("商户类型：充电商户/停车商户/充停一体商户")
    private String merchantType;

    @Schema(description = "商户地址")
    @ExcelProperty("商户地址")
    private String address;

    @Schema(description = "注册时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("注册时间")
    private LocalDateTime registerTime;

    @Schema(description = "商户状态：待审核/正常/禁用/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("商户状态：待审核/正常/禁用/已驳回")
    private String status;

    @Schema(description = "账户余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("账户余额")
    private BigDecimal walletBalance;

    @Schema(description = "审核人ID", example = "26528")
    @ExcelProperty("审核人ID")
    private Long auditorId;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

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

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}