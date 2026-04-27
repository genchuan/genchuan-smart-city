package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 优惠券分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponMgmtPageReqVO extends PageParam {

    @Schema(description = "券名称")
    private String name;

    @Schema(description = "券类型")
    private String type;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "有效期开始时间")
    private Long validStartTime;

    @Schema(description = "有效期结束时间")
    private Long validEndTime;

    @Schema(description = "日期筛选，格式如：2026-04-24")
    private String date;

    @Schema(description = "创建开始时间")
    private Long startTime;

    @Schema(description = "创建结束时间")
    private Long endTime;

}
