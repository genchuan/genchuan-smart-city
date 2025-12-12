package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 通用场景表，一级和二级场景分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UniversalScenePageReqVO extends PageParam {

    @Schema(description = "场景唯一标识", example = "9162")
    private String sceneCode;

    @Schema(description = "父级ID，0表示一级场景", example = "26486")
    private Long parentId;

    @Schema(description = "场景等级，一级场景为1，二级为2")
    private Integer level;

    @Schema(description = "场景描述", example = "你猜")
    private String description;

    @Schema(description = "场景名称，如 城管住建/市政设施")
    private String label;

    @Schema(description = "场景值，用于前端选择，如 urban_admin / urban_admin_facility")
    private String value;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
