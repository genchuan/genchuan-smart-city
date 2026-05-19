package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 积分活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PointActivityPageReqVO extends PageParam {

    @Schema(description = "活动名称")
    private String name;

    @Schema(description = "活动类型")
    private String type;

    @Schema(description = "活动状态")
    private String status;

    @Schema(description = "积分规则")
    private String rule;

    @Schema(description = "活动描述")
    private String description;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "适用场站ID")
    private Long stationId;

    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "日期筛选，格式如：2026-04-24")
    private String date;

    /**
     * 校验时间范围参数
     */
    public void validateTimeRange() {
        validateTimeArray(startTime, "开始时间");
        validateTimeArray(endTime, "结束时间");
    }

    private void validateTimeArray(LocalDateTime[] times, String label) {
        if (times == null || times.length == 0) {
            return;
        }
        if (times.length > 2) {
            throw new IllegalArgumentException(label + "范围最多传2个值");
        }
        if (times.length == 2 && times[0] != null && times[1] != null && times[0].isAfter(times[1])) {
            throw new IllegalArgumentException(label + "范围的起始时间不能晚于结束时间");
        }
    }

}
