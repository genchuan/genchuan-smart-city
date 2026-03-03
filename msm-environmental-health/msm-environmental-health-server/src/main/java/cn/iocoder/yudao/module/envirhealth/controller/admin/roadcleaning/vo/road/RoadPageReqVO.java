package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 道路分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoadPageReqVO extends PageParam {

    @Schema(description = "道路主键（UUID）", example = "20896")
    private String roadId;

    @Schema(description = "道路名称", example = "王五")
    private String roadName;

    @Schema(description = "关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "道路等级")
    private String roadLevel;

    @Schema(description = "长度，单位：公里")
    private BigDecimal length;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}