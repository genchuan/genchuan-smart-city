package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * 管理后台 - 政策解读信息 Response VO
 */
@Data
@Schema(description = "管理后台 - 政策解读信息 Response VO")
public class PolicyInterpretationInfoRespVO {

    @Schema(description = "主键ID", example = "1024")
    private Long id;

    @Schema(description = "解读ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String interpretationId;

    @Schema(description = "解读标题", example = "《关于推进智慧城市健康发展的指导意见》核心条款解读")
    private String interpretationTitle;

    @Schema(description = "关联政策类型", example = "国家政策")
    private String relatedPolicyType;

    @Schema(description = "关联政策ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String relatedPolicyId;

    @Schema(description = "关联政策名称", example = "关于推进智慧城市健康发展的指导意见")
    private String relatedPolicyName;

    @Schema(description = "解读内容")
    private String interpretationContent;

    @Schema(description = "解读类型", example = "官方解读")
    private String interpretationType;

    @Schema(description = "发布人ID", example = "admin")
    private String issueUserId;

    @Schema(description = "发布人姓名", example = "系统管理员")
    private String issueUserName;

    @Schema(description = "发布时间")
    private LocalDateTime issueTime;

    @Schema(description = "解读状态", example = "已发布")
    private String interpretationStatus;

    @Schema(description = "下架原因")
    private String offShelfReason;

    @Schema(description = "更新人ID", example = "admin")
    private String updateUserId;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "扩展分类字段", example = "重点条款解读")
    private String extendCategory;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}