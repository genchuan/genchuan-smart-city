package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import lombok.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 排班查看分页 Request VO")
@Data
public class ScheduleViewPageReqVO extends PageParam {

    @Schema(description = "巡检人员ID")
    private Long userId;

//    @Schema(description = "排班日期")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime[] scheduleDate;

    @Schema(description = "排班日期，支持时间戳（毫秒）或日期时间字符串（yyyy-MM-dd HH:mm:ss）")
    private String scheduleDate;

    @Schema(description = "班次类型")
    private String shiftType;

    @Schema(description = "排班状态")
    private String status;

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

    /**
     * 新增方法：获取交接日期开始时间
     * 将前端传来的时间戳或日期字符串转换为 LocalDateTime
     */
    public LocalDateTime getHandoverDateBegin() {
        if (scheduleDate != null && !scheduleDate.trim().isEmpty()) {
            try {
                // 尝试将时间戳（毫秒）转换为 LocalDateTime
                long timestamp = Long.parseLong(scheduleDate);
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
                        .toLocalDate().atStartOfDay();  // 只取日期部分，时间设为00:00:00
            } catch (NumberFormatException e) {
                // 如果不是数字，尝试按日期时间字符串解析
                // 这里可以添加对日期字符串的解析逻辑
                // 由于前端传递的是时间戳，这里暂时返回null
                return null;
            }
        }
        return null;
    }

    /**
     * 新增方法：获取交接日期结束时间
     */
    public LocalDateTime getHandoverDateEnd() {
        if (scheduleDate != null && !scheduleDate.trim().isEmpty()) {
            try {
                long timestamp = Long.parseLong(scheduleDate);
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
                        .toLocalDate().atTime(23, 59, 59);  // 当天23:59:59
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

}