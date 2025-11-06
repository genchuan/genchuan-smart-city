package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备分类规则配置新增/修改 Request VO")
@Data
public class DeviceCatRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分类规则ID")
    private String catRuleId;

    @Schema(description = "父类规则ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "父类规则ID不能为空")
    private String parentCatRuleId;

    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类层级不能为空")
    private String catLevel;

    @Schema(description = "分类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类代码不能为空")
    private String catCode;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类名称不能为空")
    private String catName;

    @Schema(description = "分类说明")
    private String catDesc;

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