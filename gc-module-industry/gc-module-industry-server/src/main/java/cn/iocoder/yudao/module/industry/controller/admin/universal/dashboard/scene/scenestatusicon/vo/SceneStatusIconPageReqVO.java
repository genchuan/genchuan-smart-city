package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场景状态字段图标配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SceneStatusIconPageReqVO extends PageParam {

    @Schema(description = "所属场景唯一标识码（sceneKey）")
    private String sceneCode;

    @Schema(description = "状态字段唯一标识码（对应 selectedFieldsConfig.key）")
    private String fieldCode;

    @Schema(description = "状态原始值（对应业务数据中的字段值）")
    private String rawValue;

    @Schema(description = "该状态对应的地图点位图标地址", example = "https://www.iocoder.cn")
    private String iconUrl;

    @Schema(description = "图例展示文本（legend 中显示的文字，可为空）")
    private String legendText;

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
