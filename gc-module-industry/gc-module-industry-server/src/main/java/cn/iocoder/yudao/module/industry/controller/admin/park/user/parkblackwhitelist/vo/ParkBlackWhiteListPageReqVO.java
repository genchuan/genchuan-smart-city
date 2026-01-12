package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 黑白名单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkBlackWhiteListPageReqVO extends PageParam {

    @Schema(description = "[名单类型] 如：黑名单/白名单", example = "2")
    private String listType;

    @Schema(description = "[目标类型] 如：用户/车辆", example = "2")
    private String targetType;

    @Schema(description = "[目标ID] 可为用户ID", example = "20952")
    private Long targetId;

    @Schema(description = "[车牌] 车辆车牌号")
    private String targetCarNumber;

    @Schema(description = "[列入原因] 被列入黑白名单原因", example = "不喜欢")
    private String reason;

    @Schema(description = "[生效时间] 规则生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[失效时间] 规则失效时间，永久有效为 NULL")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[状态] 如：生效/失效", example = "2")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 黑白名单相关备注说明", example = "你猜")
    private String remark;

}
