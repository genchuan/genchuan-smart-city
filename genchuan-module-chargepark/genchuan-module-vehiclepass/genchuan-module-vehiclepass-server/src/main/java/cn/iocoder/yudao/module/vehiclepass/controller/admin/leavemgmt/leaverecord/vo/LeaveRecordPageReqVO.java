package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 离场记录分页 Request VO")
@Data
public class LeaveRecordPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "入场时间，时间戳格式", example = "[\"1774998000\",\"1775011986\"]")
    private String[] enterTime;

    @Schema(description = "离场时间，时间戳格式", example = "[\"1775011986\",\"1775098386\"]")
    private String[] leaveTime;

    @Schema(description = "停车时长，单位：分钟，自动计算")
    private Integer parkDuration;

    @Schema(description = "记录状态：正常记录/异常记录", example = "正常记录")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "29836")
    private Long stationId;

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