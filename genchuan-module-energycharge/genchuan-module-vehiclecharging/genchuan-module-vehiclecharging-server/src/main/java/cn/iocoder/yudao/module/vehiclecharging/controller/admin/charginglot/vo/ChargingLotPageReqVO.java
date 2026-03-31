package cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 充电车位分页 Request VO")
@Data
public class ChargingLotPageReqVO extends PageParam {

    @Schema(description = "车位编号")
    private String lotCode;

    @Schema(description = "所属场站编码")
    private String stationCode;

    @Schema(description = "所属场站ID")
    private Long stationId;

    @Schema(description = "车位类型")
    private String lotType;

    @Schema(description = "关联充电桩编号")
    private String pileCode;

    @Schema(description = "关联充电桩ID")
    private Long pileId;

    @Schema(description = "占用时长（分钟）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] occupyTime;

    @Schema(description = "车位状态")
    private String lotStatus;

    @Schema(description = "占用超时时间（分钟）")
    private Integer occupyTimeout;

    @Schema(description = "维护原因")
    private String maintainReason;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}