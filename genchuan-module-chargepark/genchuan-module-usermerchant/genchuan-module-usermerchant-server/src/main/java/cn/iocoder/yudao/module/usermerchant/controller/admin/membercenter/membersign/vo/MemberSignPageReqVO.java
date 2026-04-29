package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;

@Schema(description = "管理后台 - 会员签到分页 Request VO")
@Data
public class MemberSignPageReqVO extends PageParam {

    @Schema(description = "用户ID", example = "28909")
    private Long userId;

    @Schema(description = "签到日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate[] signDate;

    @Schema(description = "连续签到天数")
    private Integer continuousDays;

    @Schema(description = "本次签到获得积分")
    private Integer point;

    @Schema(description = "本次签到获得经验")
    private Integer experience;

    @Schema(description = "记录状态：1-正常，0-异常", example = "1")
    private Integer status;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "签到时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}