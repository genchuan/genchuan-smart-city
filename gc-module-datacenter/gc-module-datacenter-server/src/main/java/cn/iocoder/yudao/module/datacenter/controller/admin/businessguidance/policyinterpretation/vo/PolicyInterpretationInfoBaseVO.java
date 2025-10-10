package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * 管理后台 - 政策解读信息基础 Request VO
 *
 * @author Gyh
 */
@Data
@Schema(description = "管理后台 - 政策解读信息基础 Request VO")
public class PolicyInterpretationInfoBaseVO {

    @Schema(description = "解读标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "《关于推进智慧城市健康发展的指导意见》核心条款解读")
    @NotBlank(message = "解读标题不能为空")
    @Size(max = 200, message = "解读标题长度不能超过200个字符")
    private String interpretationTitle;

    @Schema(description = "关联政策类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "国家政策")
    @NotBlank(message = "关联政策类型不能为空")
    @Size(max = 10, message = "关联政策类型长度不能超过10个字符")
    private String relatedPolicyType;

    @Schema(description = "关联政策ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "550e8400-e29b-41d4-a716-446655440000")
    @NotBlank(message = "关联政策ID不能为空")
    @Size(max = 32, message = "关联政策ID长度不能超过32个字符")
    private String relatedPolicyId;

    @Schema(description = "关联政策名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "关于推进智慧城市健康发展的指导意见")
    @NotBlank(message = "关联政策名称不能为空")
    @Size(max = 200, message = "关联政策名称长度不能超过200个字符")
    private String relatedPolicyName;

    @Schema(description = "解读内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "解读内容不能为空")
    @Size(max = 4000, message = "解读内容长度不能超过4000个字符")
    private String interpretationContent;

    @Schema(description = "解读类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "官方解读")
    @NotBlank(message = "解读类型不能为空")
    @Size(max = 20, message = "解读类型长度不能超过20个字符")
    private String interpretationType;

    @Schema(description = "扩展分类字段", example = "重点条款解读")
    @Size(max = 30, message = "扩展分类字段长度不能超过30个字符")
    private String extendCategory;
}