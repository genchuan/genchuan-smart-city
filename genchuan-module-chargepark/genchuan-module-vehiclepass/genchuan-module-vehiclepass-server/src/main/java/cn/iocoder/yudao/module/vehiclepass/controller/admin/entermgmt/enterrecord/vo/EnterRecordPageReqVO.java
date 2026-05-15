package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 入场记录分页 Request VO")
@Data
public class EnterRecordPageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/其他，关联字典enter_record_plate_color")
    private String plateColor;

    @Schema(description = "车位编号")
    private String spaceNo;

    @Schema(description = "入场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] enterTime;

    @Schema(description = "记录类型：自动识别/人工补录，关联字典enter_record_record_type", example = "2")
    private String recordType;

    @Schema(description = "记录状态：正常记录/异常记录，关联字典enter_record_status", example = "1")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "20633")
    private Long stationId;

    @Schema(description = "备注", example = "你猜")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}