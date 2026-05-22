package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 交接日志分页 Request VO")
@Data
public class HandoverLogPageReqVO extends PageParam {

    @Schema(description = "交接人员ID")
    private Long userId;

    @Schema(description = "交接日期，支持时间戳（毫秒）或日期时间字符串（yyyy-MM-dd HH:mm:ss）")
    private String handoverDate;

    @Schema(description = "趋势时间（精确到天），格式：yyyy-MM-dd", example = "2024-01-25")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) // 确保能正确绑定`yyyy-MM-dd`格式的字符串
    private String trendTime; // 新增字段，用于接收图表点击传来的具体日期

    @Schema(description = "交接内容")
    private String content;

    @Schema(description = "日志状态")
    private String status;

    @Schema(description = "确认人ID")
    private Long confirmUserId;

    @Schema(description = "确认时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] confirmTime;

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
        if (handoverDate != null && !handoverDate.trim().isEmpty()) {
            try {
                // 尝试将时间戳（毫秒）转换为 LocalDateTime
                long timestamp = Long.parseLong(handoverDate);
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
        if (handoverDate != null && !handoverDate.trim().isEmpty()) {
            try {
                long timestamp = Long.parseLong(handoverDate);
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
                        .toLocalDate().atTime(23, 59, 59);  // 当天23:59:59
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }


    /**
     * 新增方法：获取趋势时间查询的开始时间（当天 00:00:00）
     */
    public LocalDateTime getTrendTimeBegin() {
        if (trendTime != null && !trendTime.trim().isEmpty()) {
            try {
                // 解析 yyyy-MM-dd 格式的字符串
                LocalDate date = LocalDate.parse(trendTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return date.atStartOfDay(); // 返回当天的 00:00:00
            } catch (DateTimeParseException e) {
                // 如果解析失败，可以返回null或记录日志
                return null;
            }
        }
        return null;
    }

    /**
     * 新增方法：获取趋势时间查询的结束时间（当天 23:59:59）
     */
    public LocalDateTime getTrendTimeEnd() {
        if (trendTime != null && !trendTime.trim().isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(trendTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return date.atTime(23, 59, 59); // 返回当天的 23:59:59
            } catch (DateTimeParseException e) {
                return null;
            }
        }
        return null;
    }
}