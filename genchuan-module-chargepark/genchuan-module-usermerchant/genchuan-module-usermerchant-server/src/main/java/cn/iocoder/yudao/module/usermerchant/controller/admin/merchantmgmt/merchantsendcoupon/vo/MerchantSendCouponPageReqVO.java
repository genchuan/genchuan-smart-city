package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商户发券分页 Request VO")
@Data
public class MerchantSendCouponPageReqVO extends PageParam {

    @Schema(description = "商户ID，关联merchant_info.id", example = "14294")
    private Long merchantId;

    @Schema(description = "优惠券ID，关联营销模块优惠券表", example = "1926")
    private Long couponId;

    @Schema(description = "优惠券名称", example = "芋艿")
    private String couponName;

    @Schema(description = "发放数量", example = "5201")
    private Integer sendCount;

    @Schema(description = "执行时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] execTime;

    @Schema(description = "发券完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

    @Schema(description = "已核销数量", example = "31192")
    private Integer useCount;

    @Schema(description = "发券状态：待执行/已执行/已取消", example = "1")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}