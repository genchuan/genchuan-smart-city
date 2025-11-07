package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 社区（村）行政区划配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GridCommunityPageReqVO extends PageParam {

    @Schema(description = "社区ID")
    private String communityId;

    @Schema(description = "社区代码")
    private String communityCode;

    @Schema(description = "社区名称")
    private String communityName;

    @Schema(description = "所属街道ID")
    private String streetId;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "失效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] invalidTime;

    @Schema(description = "社区边界坐标")
    private String communityBoundary;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}