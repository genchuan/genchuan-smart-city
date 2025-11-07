package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备关联规则配置新增/修改 Request VO")
@Data
public class DeviceRelRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "关联规则ID")
    private String relRuleId;

    @Schema(description = "设备分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备分类ID不能为空")
    private String deviceCatId;

    @Schema(description = "设备分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备分类名称不能为空")
    private String deviceCatName;

    @Schema(description = "关联对象类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联对象类型不能为空")
    private String relObjType;

    @Schema(description = "关联对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联对象ID不能为空")
    private String relObjId;

    @Schema(description = "关联对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联对象名称不能为空")
    private String relObjName;

    @Schema(description = "关联必选标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联必选标识不能为空")
    private String isRequired;

    @Schema(description = "关联校验规则")
    private String relCkRule;

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