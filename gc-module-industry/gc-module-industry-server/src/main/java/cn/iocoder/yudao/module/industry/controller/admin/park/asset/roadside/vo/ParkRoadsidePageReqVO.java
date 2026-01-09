package cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 路侧泊位分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkRoadsidePageReqVO extends PageParam {

    @Schema(description = "关联ID", example = "7766")
    private String assetExtendId;

    @Schema(description = "道路名称", example = "赵六")
    private String roadName;

    @Schema(description = "唯一泊位编号")
    private String berthNumber;

    @Schema(description = "泊位类型", example = "2")
    private String berthType;

    @Schema(description = "所属计费桩", example = "11757")
    private String feePileId;

    @Schema(description = "占用状态", example = "1")
    private String status;

    @Schema(description = "上次占用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastOccupyTime;

    @Schema(description = "上次释放时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastReleaseTime;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] roadsideCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] roadsideUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String roadsideRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}