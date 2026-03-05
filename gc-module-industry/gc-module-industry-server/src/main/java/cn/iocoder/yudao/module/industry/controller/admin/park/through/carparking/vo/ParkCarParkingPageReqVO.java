package cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 在停车辆分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkCarParkingPageReqVO extends PageParam {

    @Schema(description = "在停记录ID（UUID）", example = "7852")
    private String parkingId;

    @Schema(description = "入场记录ID", example = "8656")
    private String entryId;

    @Schema(description = "车牌")
    private String carNumber;

    @Schema(description = "所属车场ID", example = "9294")
    private String lotId;

    @Schema(description = "车位ID", example = "30221")
    private String spaceId;

    @Schema(description = "已停放时长（分钟）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] parkingTime;

    @Schema(description = "状态：正常/疑似套牌/异常", example = "2")
    private String parkingStatus;

    @Schema(description = "疑似套牌原因", example = "不喜欢")
    private String suspiciousReason;

    @Schema(description = "异常原因", example = "不香")
    private String abnormalReason;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] parkingUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String parkingRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}