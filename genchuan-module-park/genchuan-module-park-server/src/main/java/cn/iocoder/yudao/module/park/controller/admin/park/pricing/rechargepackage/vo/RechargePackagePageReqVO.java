package cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 充值套餐分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargePackagePageReqVO extends PageParam {

    @Schema(description = "[套餐名称]", example = "赵六")
    private String packageName;

    @Schema(description = "[充值金额]")
    private BigDecimal rechargeAmount;

    @Schema(description = "[赠送金额/时长] JSON格式varchar")
    private String giveAmount;

    @Schema(description = "[赠送内容有效期] 可为NULL")
    private Integer validDays;

    @Schema(description = "[套餐类型] 如:金额套餐/时长套餐", example = "1")
    private String packageType;

    @Schema(description = "[状态] 如:上架/下架", example = "2")
    private String status;

    @Schema(description = "[销量]", example = "30218")
    private Integer salesCount;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
