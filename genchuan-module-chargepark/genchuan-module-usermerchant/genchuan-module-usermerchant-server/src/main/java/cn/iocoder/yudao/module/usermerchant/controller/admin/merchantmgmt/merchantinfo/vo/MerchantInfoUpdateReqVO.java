package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 商户信息新增/修改 Request VO")
@Data
public class MerchantInfoUpdateReqVO {

    @Schema(description = "主键ID", example = "3360")
    private Long id;

    @TableField(exist = false)
    @Schema(description = "主键ID组")
    private List<Long> ids;

    @Schema(description = "商户名称，唯一", example = "赵六")
    @NotEmpty(message = "商户名称，唯一不能为空")
    private String name;

    @Schema(description = "联系人")
    @NotEmpty(message = "联系人不能为空")
    private String contact;

    @Schema(description = "联系手机号")
    @NotEmpty(message = "联系手机号不能为空")
    private String phone;

    @Schema(description = "商户类型：充电商户/停车商户/充停一体商户", example = "1")
    @NotEmpty(message = "商户类型：充电商户/停车商户/充停一体商户不能为空")
    private String merchantType;

    @Schema(description = "商户地址")
    private String address;

    @Schema(description = "注册时间")
    @NotNull(message = "注册时间不能为空")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime registerTime;

    @Schema(description = "商户状态：待审核/正常/禁用/已驳回", example = "1")
    @NotEmpty(message = "商户状态：待审核/正常/禁用/已驳回不能为空")
    private String status;

    @Schema(description = "账户余额")
    @NotNull(message = "账户余额不能为空")
    private BigDecimal walletBalance;

    @Schema(description = "审核人ID", example = "26528")
    private Long auditorId;

    @Schema(description = "审核结果")
    private String auditResult;

    @Schema(description = "审核时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime auditTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}