package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测事件类别分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventTypePageReqVO extends PageParam {

    @Schema(description = "上级事件类别ID", example = "28494")
    private Long parentTypeId;

    @Schema(description = "唯一事件编码")
    private String eventCode;

    @Schema(description = "事件名称", example = "王五")
    private String eventName;

    @Schema(description = "关联监测部件类别ID", example = "11891")
    private Long relatedMonitorTypeId;

    @Schema(description = "默认告警等级：提示/一般/严重/紧急")
    private String alarmLevel;

    @Schema(description = "事件处理规则")
    private String handleRule;

    @Schema(description = "状态：启用/停用", example = "1")
    private String eventStatus;

    @Schema(description = "业务备注", example = "你猜")
    private String eventRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
