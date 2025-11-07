package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Schema(description = "管理后台 - 国家政策信息 Response VO")
@Data
public class NationalPolicyInfoRespVO {

    @Schema(description = "主键ID", example = "1024")
    private Long id;

    @Schema(description = "政策ID，唯一编码，采用UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String policyId;

    @Schema(description = "政策编号", example = "国办发〔2025〕1号")
    private String policyNo;

    @Schema(description = "政策完整名称", example = "关于推进智慧城市健康发展的指导意见")
    private String policyName;

    @Schema(description = "政策类型", example = "规划类")
    private String policyType;

    @Schema(description = "发布单位", example = "国务院办公厅")
    private String issueDept;

    @Schema(description = "发布时间")
    private LocalDateTime issueTime;

    @Schema(description = "生效时间")
    private LocalDate effectiveTime;

    @Schema(description = "失效时间")
    private LocalDate expireTime;

    @Schema(description = "政策原文在服务器的存储路径")
    private String policyFilePath;

    @Schema(description = "政策摘要")
    private String policyAbstract;

    @Schema(description = "政策状态", example = "有效")
    private String policyStatus;

    @Schema(description = "上传人")
    private String uploadUserId;

    @Schema(description = "上传时间")
    private LocalDateTime uploadTime;

    @Schema(description = "更新人")
    private String updateUserId;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "扩展分类字段")
    private String extendCategory;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}