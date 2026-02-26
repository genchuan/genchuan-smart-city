package cn.iocoder.yudao.module.envir.controller.admin.road.vo;

import lombok.*;
import java.util.*;
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

    @Schema(description = "业务主键（UUID）", example = "13641")
    private String sysRoadId;

    @Schema(description = "道路名称", example = "王五")
    private String name;

    @Schema(description = "道路编码")
    private String code;

    @Schema(description = "道路长度")
    private BigDecimal length;

    @Schema(description = "道路宽度")
    private BigDecimal width;

    @Schema(description = "道路类型", example = "1")
    private String roadType;

    @Schema(description = "状态：启用/禁用", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

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