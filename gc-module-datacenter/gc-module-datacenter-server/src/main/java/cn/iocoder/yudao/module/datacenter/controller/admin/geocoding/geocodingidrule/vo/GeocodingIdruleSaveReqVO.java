package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标识码编码规则配置新增/修改 Request VO")
@Data
public class GeocodingIdruleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "规则ID")
    private String idRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则名称不能为空")
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
    private LocalDateTime configTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

}