package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 在停状态分页 Request VO")
@Data
public class InParkStatusPageReqVO extends PageParam {

    @Schema(description = "场站ID，关联场站表station_info", example = "14863")
    private Long stationId;

    @Schema(description = "场站名称，支持模糊查询")
    private String stationName;

    @Schema(description = "车位ID，关联车位表parking_space_info", example = "16153")
    private Long spaceId;

    @Schema(description = "车牌号码")
    private String carNo;

    @Schema(description = "入场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inTime;

    @Schema(description = "是否超时长：是/否，关联字典in_park_status_over_time")
    private String overTime;

    @Schema(description = "状态：正常/异常，关联字典in_park_status_status", example = "1")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

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