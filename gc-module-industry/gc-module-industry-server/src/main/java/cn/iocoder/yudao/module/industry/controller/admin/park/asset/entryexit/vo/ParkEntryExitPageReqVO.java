package cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo;

import lombok.*;

import java.time.LocalTime;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 出入口信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkEntryExitPageReqVO extends PageParam {

    @Schema(description = "关联ID", example = "21088")
    private String assetExtendId;

    @Schema(description = "所属车场ID", example = "5711")
    private String lotId;

    @Schema(description = "方向：入口/出口/双向")
    private String direction;

    @Schema(description = "关联设备ID列表")
    private String deviceIds;

    @Schema(description = "通行规则ID", example = "25093")
    private String passRuleId;

    @Schema(description = "开放时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] openTime;

    @Schema(description = "关闭时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] closeTime;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryExitCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryExitUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String entryExitRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}