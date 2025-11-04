package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 地理编码分段组合规则配置新增/修改 Request VO")
@Data
public class GeocodingSegmentruleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "规则ID")
    private String segmentRuleId;

    @Schema(description = "定位对象类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "定位对象类型不能为空")
    private String locationType;

    @Schema(description = "分段规则")
    private String segmentRule;

    @Schema(description = "组合规则")
    private String combineRule;

    @Schema(description = "方位描述选项")
    private String directionOptions;

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