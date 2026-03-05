package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 运维排班分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkMaintainSchedulePageReqVO extends PageParam {

    @Schema(description = "[运维人员ID] 关联 park_maintain_user.id", example = "25396")
    private Long maintainUserId;

    @Schema(description = "[排班日期] 排班日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] scheduleDate;

    @Schema(description = "[班次类型] 早班 / 中班 / 晚班 / 全天", example = "1")
    private String shiftType;

    @Schema(description = "[上班时间] 上班时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] startTime;

    @Schema(description = "[下班时间] 下班时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] endTime;

    @Schema(description = "[状态] 排班状态：正常 / 调班 / 取消", example = "1")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

    @Schema(description = "[备注] 排班相关备注说明", example = "随便")
    private String remark;

}
