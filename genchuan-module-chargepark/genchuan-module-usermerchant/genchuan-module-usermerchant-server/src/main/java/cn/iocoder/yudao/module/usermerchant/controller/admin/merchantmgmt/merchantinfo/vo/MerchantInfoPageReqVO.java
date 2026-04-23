package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商户信息分页 Request VO")
@Data
public class MerchantInfoPageReqVO extends PageParam {

    @Schema(description = "商户名称，唯一", example = "赵六")
    private String name;

    @Schema(description = "联系人")
    private String contact;

    @Schema(description = "联系手机号")
    private String phone;

    @Schema(description = "商户类型：充电商户/停车商户/充停一体商户", example = "充电商户")
    private String merchantType;

    @Schema(description = "商户地址")
    private String address;

    @Schema(description = "注册时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] registerTime;

    @Schema(description = "商户状态：待审核/正常/禁用/已驳回", example = "待审核")
    private String status;

    @Schema(description = "账户余额")
    private BigDecimal walletBalance;

    @Schema(description = "审核人ID", example = "1")
    private Long auditorId;

    @TableField(exist = false)
    @Schema(description = "审核人名称")
    private String auditorName;

    @Schema(description = "审核时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] auditTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}