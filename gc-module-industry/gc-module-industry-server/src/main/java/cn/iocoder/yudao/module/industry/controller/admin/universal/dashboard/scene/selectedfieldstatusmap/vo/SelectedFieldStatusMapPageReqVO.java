package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场景字段状态映射分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SelectedFieldStatusMapPageReqVO extends PageParam {

    @Schema(description = "所属场景唯一标识码（sceneKey）")
    private String sceneCode;

    @Schema(description = "字段唯一标识码（对应 selectedFieldsConfig.key，仅 status 类型字段有效）")
    private String fieldCode;

    @Schema(description = "状态原始值（来自业务数据，如“正常开放”）")
    private String rawValue;

    @Schema(description = "状态展示文本（前端显示用）")
    private String displayText;

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
