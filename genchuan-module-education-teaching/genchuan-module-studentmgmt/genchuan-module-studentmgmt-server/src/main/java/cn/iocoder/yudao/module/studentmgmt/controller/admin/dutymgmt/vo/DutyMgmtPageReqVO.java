package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 值班管理分页 Request VO")
@Data
public class DutyMgmtPageReqVO extends PageParam {

    @Schema(description = "值班日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] dutyDate;

    @Schema(description = "值班人")
    private String dutyUser;

    @Schema(description = "打卡时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkInTime;

    @Schema(description = "打卡状态：未打卡/已打卡", example = "1")
    private String checkInStatus;

    @Schema(description = "调班原因", example = "不香")
    private String transferReason;

    @Schema(description = "调班替代人")
    private String transferUser;

    @Schema(description = "调班状态：无/待审批/已通过/已驳回", example = "1")
    private String transferStatus;

    @Schema(description = "出车事由", example = "不对")
    private String carReason;

    @Schema(description = "出车目的地")
    private String carDestination;

    @Schema(description = "出车状态：无/待审批/已通过", example = "2")
    private String carStatus;

    @Schema(description = "值班记录")
    private String recordContent;

    @Schema(description = "记录上传时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] recordUploadTime;

    @Schema(description = "状态：待打卡/待调班审批/待出车审批/已完成", example = "1")
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