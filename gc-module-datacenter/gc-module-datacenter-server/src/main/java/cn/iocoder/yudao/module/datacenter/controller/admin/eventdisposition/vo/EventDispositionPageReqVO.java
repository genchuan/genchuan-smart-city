package cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测事件关联处置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventDispositionPageReqVO extends PageParam {

    @Schema(description = "事件类型ID")
    private String eventTypeId;

    @Schema(description = "事件类型名称")
    private String eventTypeName;

    @Schema(description = "行政区划编码")
    private String divisionCode;

    @Schema(description = "行政区划名称")
    private String divisionName;

    @Schema(description = "流程模型ID")
    private String processModelId;

    @Schema(description = "流程模型名称")
    private String processModelName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}