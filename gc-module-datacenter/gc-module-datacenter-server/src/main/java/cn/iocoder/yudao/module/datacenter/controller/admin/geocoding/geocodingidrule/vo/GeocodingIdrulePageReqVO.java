package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 标识码编码规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeocodingIdrulePageReqVO extends PageParam {

    @Schema(description = "规则ID")
    private String idRuleId;

    @Schema(description = "规则名称")
    private String ruleName;

    @Schema(description = "县级行政代码位数")
    private Integer adminCodeLength;

    @Schema(description = "街道代码位数")
    private Integer streetCodeLength;

    @Schema(description = "图层代码位数")
    private Integer layerCodeLength;

    @Schema(description = "顺序码位数")
    private Integer seqCodeLength;

    @Schema(description = "顺序码生成规则")
    private String seqGenRule;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] configTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}