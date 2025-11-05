package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格专题图层管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GridTopicLayerPageReqVO extends PageParam {

    @Schema(description = "图层ID")
    private String layerId;

    @Schema(description = "图层名称")
    private String layerName;

    @Schema(description = "网格类型")
    private String gridType;

    @Schema(description = "比例尺")
    private String scale;

    @Schema(description = "边界线样式ID")
    private String boundaryStyleId;

    @Schema(description = "注记样式ID")
    private String annotateStyleId;

    @Schema(description = "显示状态")
    private String displayStatus;

    @Schema(description = "图层顺序")
    private Integer layerWo;

    @Schema(description = "创建人")
    private String createUserId;

    @Schema(description = "备注")
    private String remark;

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