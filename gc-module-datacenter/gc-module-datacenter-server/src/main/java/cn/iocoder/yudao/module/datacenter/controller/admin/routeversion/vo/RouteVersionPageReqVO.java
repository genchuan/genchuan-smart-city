package cn.iocoder.yudao.module.datacenter.controller.admin.routeversion.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 路线版本分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteVersionPageReqVO extends PageParam {

    @Schema(description = "路线ID")
    private String routeId;

    @Schema(description = "路线名称")
    private String routeName;

    @Schema(description = "版本号")
    private String versionNumber;

    @Schema(description = "版本描述")
    private String versionDescription;

    @Schema(description = "变更原因")
    private String changeReason;

    @Schema(description = "变更内容")
    private String changeContent;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}