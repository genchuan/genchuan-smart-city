package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场景字段分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SceneFieldPageReqVO extends PageParam {

    @Schema(description = "所属场景id", example = "5430")
    private String sceneCode;

    @Schema(description = "唯一字段标识", example = "23799")
    private String fieldCode;

    @Schema(description = "字段名称", example = "设施名称")
    private String label;

    @Schema(description = "字段类型：normal/status/coord", example = "normal")
    private String type;



}
