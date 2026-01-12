package cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 入场记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkCarEntryPageReqVO extends PageParam {

    @Schema(description = "入场记录ID（UUID）", example = "25052")
    private String entryId;

    @Schema(description = "车牌号码")
    private String carNumber;

    @Schema(description = "车辆类型", example = "2")
    private String carType;

    @Schema(description = "入场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryTime;

    @Schema(description = "入场出入口ID", example = "26377")
    private String entryExitId;

    @Schema(description = "所属车场ID", example = "2671")
    private String lotId;

    @Schema(description = "分配车位ID", example = "18134")
    private String spaceId;

    @Schema(description = "识别设备")
    private String deviceCode;

    @Schema(description = "入场类型：正常识别/无牌车/特殊放行", example = "2")
    private String entryType;

    @Schema(description = "预约用户ID", example = "11265")
    private Long userId;

    @Schema(description = "预约ID", example = "7069")
    private String reservationId;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    private String entryRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}