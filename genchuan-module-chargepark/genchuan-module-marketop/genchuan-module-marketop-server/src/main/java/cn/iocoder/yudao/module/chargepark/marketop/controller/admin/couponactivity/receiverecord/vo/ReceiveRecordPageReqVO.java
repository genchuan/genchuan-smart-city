package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 领用记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReceiveRecordPageReqVO extends PageParam {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "优惠券ID")
    private Long couponId;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "同步状态")
    private String syncStatus;

    @Schema(description = "领取开始时间")
    private Long receiveStartTime;

    @Schema(description = "领取结束时间")
    private Long receiveEndTime;

    @Schema(description = "日期筛选，格式如：2026-04-24")
    private String date;

    @Schema(description = "创建开始时间")
    private Long startTime;

    @Schema(description = "创建结束时间")
    private Long endTime;

}
