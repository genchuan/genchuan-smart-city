package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检督办进度跟踪视图分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectSupvTrackViewPageReqVO extends PageParam {

    @Schema(description = "督办任务ID")
    private String supvTaskId;

    @Schema(description = "督办任务编码")
    private String supvTaskCode;

    @Schema(description = "关联问题名称")
    private String problemName;

    @Schema(description = "督办人姓名")
    private String supvUserName;

    @Schema(description = "整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "督办任务状态")
    private String supvStatus;

    @Schema(description = "督办截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] supvDeadlineTime;

    @Schema(description = "是否超期")
    private Boolean isOverdue;

    @Schema(description = "最新督办进展时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestSupvTime;

    @Schema(description = "最新督办进展类型")
    private String latestSupvType;

    @Schema(description = "最新督办进展内容")
    private String latestSupvContent;

    @Schema(description = "督办操作次数")
    private Integer supvOperCount;

    @Schema(description = "关联整改任务状态")
    private String rectifyStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}