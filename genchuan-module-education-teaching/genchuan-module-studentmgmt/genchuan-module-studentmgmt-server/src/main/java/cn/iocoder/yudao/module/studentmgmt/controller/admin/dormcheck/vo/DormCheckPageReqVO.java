package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 宿舍考勤分页 Request VO")
@Data
public class DormCheckPageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "14725")
    private Long studentId;

    @Schema(description = "考勤时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkTime;

    @Schema(description = "考勤状态：正常/迟到/未到", example = "1")
    private String checkStatus;

    @Schema(description = "异常类型：无/晚归/未归", example = "1")
    private String abnormalType;

    @Schema(description = "补卡时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] repairTime;

    @Schema(description = "补卡人")
    private String repairUser;

    @Schema(description = "推送时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] pushTime;

    @Schema(description = "在寝率")
    private BigDecimal inRate;

    @Schema(description = "状态：正常/异常", example = "2")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}