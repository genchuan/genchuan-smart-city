package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 巡检轨迹分页 Request VO")
@Data
public class InspectTrackPageReqVO extends PageParam {

    @Schema(description = "巡检人员ID")
    private Long userId;

    @Schema(description = "轨迹时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] trackTime;

    @Schema(description = "巡检里程（公里）")
    private BigDecimal mileage;

    @Schema(description = "巡检时长（分钟）")
    private Integer duration;

    @Schema(description = "所属片区")
    private String area;

    @Schema(description = "轨迹状态")
    private String status;

    @Schema(description = "轨迹点")
    private String points;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "核查状态：0-未核查 1-已核查 2-核查中")
    private String checkStatus;

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