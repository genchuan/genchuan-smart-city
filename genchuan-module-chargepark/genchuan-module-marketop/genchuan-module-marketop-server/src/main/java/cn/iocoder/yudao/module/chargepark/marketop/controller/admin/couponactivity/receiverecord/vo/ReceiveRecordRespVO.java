package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 领用记录 Response VO")
@Data
public class ReceiveRecordRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "记录编号")
    private String no;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "优惠券ID")
    private Long couponId;

    @Schema(description = "领取时间")
    private LocalDateTime receiveTime;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "核销时间")
    private LocalDateTime verifyTime;

    @Schema(description = "核查结果")
    private String checkResult;

    @Schema(description = "同步状态")
    private String syncStatus;

    @Schema(description = "归档时间")
    private LocalDateTime archiveTime;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
