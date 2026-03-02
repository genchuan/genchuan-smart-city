package cn.iocoder.yudao.module.envirhealth.controller.admin.area.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 区域编码分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AreaPageReqVO extends PageParam {

    @Schema(description = "主键（区域编码）")
    private String areaCode;

    @Schema(description = "区域名称", example = "张三")
    private String areaName;

    @Schema(description = "上级区域编码（关联sys_area.area_code，顶级区域填0）")
    private String parentCode;

    @Schema(description = "区域层级（可选值：1-省级/2-市级/3-区级/4-街道/5-社区）")
    private Integer level;

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