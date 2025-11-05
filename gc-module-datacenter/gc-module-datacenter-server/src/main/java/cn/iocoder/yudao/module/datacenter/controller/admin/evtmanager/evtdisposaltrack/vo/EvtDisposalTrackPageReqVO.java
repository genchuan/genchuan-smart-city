package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 事件处置跟踪分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvtDisposalTrackPageReqVO extends PageParam {

    @Schema(description = "跟踪ID，唯一编码，UUID", example = "3982")
    private String trackId;

    @Schema(description = "工单ID，关联事件工单表", example = "20358")
    private String woId;

    @Schema(description = "处置状态", example = "1")
    private String handleStatus;

    @Schema(description = "当前处置节点")
    private String currentNode;

    @Schema(description = "处置人员", example = "张三")
    private String handlerName;

    @Schema(description = "处置开始时间，格式yyyy - MM - dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "预计完成时间，格式yyyy - MM - dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] estCompleteTime;

}