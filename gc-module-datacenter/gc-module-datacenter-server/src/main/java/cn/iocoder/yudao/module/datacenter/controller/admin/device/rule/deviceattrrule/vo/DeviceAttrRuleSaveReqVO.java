package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备属性规则配置新增/修改 Request VO")
@Data
public class DeviceAttrRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "属性规则ID")
    private String attrRuleId;

    @Schema(description = "设备分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备分类ID不能为空")
    private String deviceCatId;

    @Schema(description = "设备分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备分类名称不能为空")
    private String deviceCatName;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性名称不能为空")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "属性代码不能为空")
    private String attrCode;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    @Schema(description = "字段长度")
    private Integer fieldLength;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否必选不能为空")
    private String isRequired;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "值域范围")
    private String valueRange;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "属性说明")
    private String attrDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}