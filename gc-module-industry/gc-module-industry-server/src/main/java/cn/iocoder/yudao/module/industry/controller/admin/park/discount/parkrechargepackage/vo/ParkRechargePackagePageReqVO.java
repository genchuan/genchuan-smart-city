package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 充值套餐分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkRechargePackagePageReqVO extends PageParam {

    @Schema(description = "[套餐名称] 充值套餐名称", example = "赵六")
    private String packageName;

    @Schema(description = "[充值金额] 实际充值金额")
    private BigDecimal rechargeAmount;

    @Schema(description = "[赠送金额] 充值赠送金额")
    private BigDecimal giveAmount;

    @Schema(description = "[赠送时间] 赠送时间，单位分钟")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] giveTime;

    @Schema(description = "[状态] 如:上架/下架", example = "2")
    private String status;

    @Schema(description = "[销售数量] 套餐销售数量", example = "29325")
    private Integer salesCount;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 充值套餐相关备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
