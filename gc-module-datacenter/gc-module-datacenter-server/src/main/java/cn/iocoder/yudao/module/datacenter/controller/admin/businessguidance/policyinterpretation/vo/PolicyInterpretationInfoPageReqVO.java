package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理后台 - 政策解读信息分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "管理后台 - 政策解读信息分页 Request VO")
public class PolicyInterpretationInfoPageReqVO extends PageParam {

    @Schema(description = "解读标题", example = "智慧城市")
    private String interpretationTitle;

    @Schema(description = "关联政策名称", example = "指导意见")
    private String relatedPolicyName;

    @Schema(description = "关联政策类型", example = "国家政策")
    private String relatedPolicyType;

    @Schema(description = "解读类型", example = "官方解读")
    private String interpretationType;

    @Schema(description = "解读状态", example = "已发布")
    private String interpretationStatus;
}