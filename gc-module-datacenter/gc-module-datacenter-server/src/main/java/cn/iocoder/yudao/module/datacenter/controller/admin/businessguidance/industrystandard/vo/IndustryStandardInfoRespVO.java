package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 管理后台 - 行业规范信息 Response VO
 */
@Data
@Schema(description = "管理后台 - 行业规范信息 Response VO")
public class IndustryStandardInfoRespVO {

    @Schema(description = "主键ID", example = "1024")
    private Long id;

    @Schema(description = "规范ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String standardId;

    @Schema(description = "规范编号", example = "GB/T 12345-2025")
    private String standardNo;

    @Schema(description = "规范名称", example = "智慧城市数据安全规范")
    private String standardName;

    @Schema(description = "行业领域", example = "数据安全")
    private String industryField;

    @Schema(description = "发布机构", example = "国家市场监督管理总局")
    private String issueOrganization;

    @Schema(description = "发布时间")
    private LocalDateTime issueTime;

    @Schema(description = "实施时间")
    private LocalDate implementationTime;

    @Schema(description = "替代旧规范号", example = "替代GB/T 12345-2020")
    private String replaceOldNo;

    @Schema(description = "规范原文路径")
    private String standardFilePath;

    @Schema(description = "配套解读路径")
    private String interpretationFilePath;

    @Schema(description = "规范摘要")
    private String standardAbstract;

    @Schema(description = "规范状态", example = "实施中")
    private String standardStatus;

    @Schema(description = "关联业务模块", example = "管理部件事项管理")
    private String relatedBusinessModule;

    @Schema(description = "上传时间")
    private LocalDateTime uploadTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "扩展分类字段", example = "强制性规范")
    private String extendCategory;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}