package cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 特殊放行分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkSpecialReleasePageReqVO extends PageParam {

    @Schema(description = "特殊放行ID（UUID）", example = "23222")
    private String releaseId;

    @Schema(description = "车牌")
    private String carNumber;

    @Schema(description = "放行类型：紧急开闸/特殊车辆/其他", example = "1")
    private String releaseType;

    @Schema(description = "放行原因", example = "不对")
    private String reason;

    @Schema(description = "出入口ID", example = "8386")
    private String entryExitId;

    @Schema(description = "放行操作人ID")
    private Long releaseBy;

    @Schema(description = "放行时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] releaseTime;

    @Schema(description = "核验状态：已核验/未核验", example = "2")
    private String verifyStatus;

    @Schema(description = "核验人ID")
    private Long verifyBy;

    @Schema(description = "核验时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] verifyTime;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] releaseCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] releaseUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String releaseRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}