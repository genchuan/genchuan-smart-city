package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 县级及以上行政区划配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GridCountyPageReqVO extends PageParam {

    @Schema(description = "行政区划ID")
    private String adminDivisionId;

    @Schema(description = "行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称")
    private String adminName;

    @Schema(description = "行政区划级别(1-省级,2-市级,3-县级)")
    private Integer adminLevel;

    @Schema(description = "上级行政区划ID")
    private String parentAdminId;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "失效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] invalidTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}