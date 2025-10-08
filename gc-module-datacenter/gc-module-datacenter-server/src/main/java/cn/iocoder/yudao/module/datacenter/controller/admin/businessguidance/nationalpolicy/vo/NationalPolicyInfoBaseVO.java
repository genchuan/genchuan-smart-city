package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Schema(description = "管理后台 - 国家政策信息基础 Request VO")
@Data
public class NationalPolicyInfoBaseVO {

    @Schema(description = "政策编号，如\"国办发〔2025〕XX号\"", requiredMode = Schema.RequiredMode.REQUIRED, example = "国办发〔2025〕1号")
    @NotBlank(message = "政策编号不能为空")
    @Size(max = 50, message = "政策编号长度不能超过50个字符")
    private String policyNo;

    @Schema(description = "政策完整名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "关于推进智慧城市健康发展的指导意见")
    @NotBlank(message = "政策名称不能为空")
    @Size(max = 200, message = "政策名称长度不能超过200个字符")
    private String policyName;

    @Schema(description = "政策类型，规划类/管理类/保障类/技术类", requiredMode = Schema.RequiredMode.REQUIRED, example = "规划类")
    @NotBlank(message = "政策类型不能为空")
    @Size(max = 30, message = "政策类型长度不能超过30个字符")
    private String policyType;

    @Schema(description = "发布单位，如\"国务院办公厅\"\"国家发展改革委\"", requiredMode = Schema.RequiredMode.REQUIRED, example = "国务院办公厅")
    @NotBlank(message = "发布单位不能为空")
    @Size(max = 100, message = "发布单位长度不能超过100个字符")
    private String issueDept;

    @Schema(description = "发布时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发布时间不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效时间不能为空")
    private LocalDate effectiveTime;

    @Schema(description = "失效时间，永久有效则为空", example = "2025-12-31")
    private LocalDate expireTime;

    @Schema(description = "政策原文在服务器的存储路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "政策文件路径不能为空")
    @Size(max = 255, message = "政策文件路径长度不能超过255个字符")
    private String policyFilePath;

    @Schema(description = "政策摘要，简要描述政策核心内容", example = "明确智慧城市建设的5项重点任务及保障措施")
    @Size(max = 1000, message = "政策摘要长度不能超过1000个字符")
    private String policyAbstract;

    @Schema(description = "扩展分类字段，预留用于自定义标签", example = "核心政策")
    @Size(max = 30, message = "扩展分类字段长度不能超过30个字符")
    private String extendCategory;
}