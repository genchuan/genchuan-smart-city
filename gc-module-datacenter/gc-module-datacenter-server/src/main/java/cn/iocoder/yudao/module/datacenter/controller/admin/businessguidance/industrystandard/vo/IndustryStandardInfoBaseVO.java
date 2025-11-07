package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 管理后台 - 行业规范信息基础 Request VO
 *
 * @author Gyh
 */
@Data
@Schema(description = "管理后台 - 行业规范信息基础 Request VO")
public class IndustryStandardInfoBaseVO {

    @Schema(description = "规范编号，如\"GB/T XXXXX-2025\"", requiredMode = Schema.RequiredMode.REQUIRED, example = "GB/T 12345-2025")
    @NotBlank(message = "规范编号不能为空")
    @Size(max = 50, message = "规范编号长度不能超过50个字符")
    private String standardNo;

    @Schema(description = "规范名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "智慧城市数据安全规范")
    @NotBlank(message = "规范名称不能为空")
    @Size(max = 200, message = "规范名称长度不能超过200个字符")
    private String standardName;

    @Schema(description = "行业领域", requiredMode = Schema.RequiredMode.REQUIRED, example = "数据安全")
    @NotBlank(message = "行业领域不能为空")
    @Size(max = 30, message = "行业领域长度不能超过30个字符")
    private String industryField;

    @Schema(description = "发布机构", requiredMode = Schema.RequiredMode.REQUIRED, example = "国家市场监督管理总局")
    @NotBlank(message = "发布机构不能为空")
    @Size(max = 100, message = "发布机构长度不能超过100个字符")
    private String issueOrganization;

    @Schema(description = "发布时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发布时间不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "实施时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实施时间不能为空")
    private LocalDate implementationTime;

    @Schema(description = "替代旧规范号", example = "替代GB/T 12345-2020")
    @Size(max = 50, message = "替代旧规范号长度不能超过50个字符")
    private String replaceOldNo;

    @Schema(description = "规范原文路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "规范文件路径不能为空")
    @Size(max = 255, message = "规范文件路径长度不能超过255个字符")
    private String standardFilePath;

    @Schema(description = "配套解读路径")
    @Size(max = 255, message = "配套解读路径长度不能超过255个字符")
    private String interpretationFilePath;

    @Schema(description = "规范摘要")
    @Size(max = 1000, message = "规范摘要长度不能超过1000个字符")
    private String standardAbstract;

    @Schema(description = "关联业务模块", example = "管理部件事项管理")
    @Size(max = 100, message = "关联业务模块长度不能超过100个字符")
    private String relatedBusinessModule;

    @Schema(description = "扩展分类字段", example = "强制性规范")
    @Size(max = 30, message = "扩展分类字段长度不能超过30个字符")
    private String extendCategory;
}