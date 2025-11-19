package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 规则分类管理新增/修改 Request VO")
@Data
public class EvalRuleCatSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "规则分类ID")
    private String ruleCatId;

    @Schema(description = "规则分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "规则分类名称不能为空")
    private String ruleCatName;

    @Schema(description = "分类编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类编码不能为空")
    private String ruleCatCode;

    @Schema(description = "适用指标体系ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用指标体系ID不能为空")
    private String idxSystemId;

    @Schema(description = "适用指标体系名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用指标体系名称不能为空")
    private String idxSystemName;

    @Schema(description = "分类描述")
    private String catDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "启用状态不能为空")
    private String enableStatus;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createUser;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间(系统)")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间(系统)")
    private LocalDateTime updateTimeSys;

}