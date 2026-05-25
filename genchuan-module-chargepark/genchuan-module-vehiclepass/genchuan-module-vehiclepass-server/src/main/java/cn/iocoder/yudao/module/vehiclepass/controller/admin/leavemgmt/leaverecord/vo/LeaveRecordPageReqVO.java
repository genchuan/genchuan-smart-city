package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(description = "管理后台 - 离场记录分页 Request VO")
@Data
public class LeaveRecordPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "入场时间，时间范围", example = "2026-04-09 00:00:00,2026-04-09 23:59:59")
    private LocalDateTime[] enterTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "离场时间，时间范围", example = "2026-04-10 00:00:00,2026-04-10 23:59:59")
    private LocalDateTime[] leaveTime;

    @Schema(description = "离场小时筛选，格式如 08:00、14:30")
    private String leaveTimeHour;

    @Schema(description = "停车时长，单位：分钟，自动计算")
    private Integer parkDuration;

    @Schema(description = "记录状态：正常记录/异常记录", example = "正常记录")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "29836")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "佐证图片地址")
    private String proofImage;

    @Schema(description = "修正日志标记：0-未修正 1-已修正 2-已确认")
    private Integer isCorrected;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

}