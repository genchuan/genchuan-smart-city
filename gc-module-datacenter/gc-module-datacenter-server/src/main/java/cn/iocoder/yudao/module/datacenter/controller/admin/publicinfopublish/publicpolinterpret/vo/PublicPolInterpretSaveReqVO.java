package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 政策解读发布新增/修改 Request VO")
@Data
public class PublicPolInterpretSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6285")
    private Long id;

    @Schema(description = "解读ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "18009")
    @NotEmpty(message = "解读ID，唯一编码，UUID生成不能为空")
    private String interpretId;

    @Schema(description = "解读标题，含政策名称，如“2025人才补贴政策解读”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解读标题，含政策名称，如“2025人才补贴政策解读”不能为空")
    private String interpretTitle;

    @Schema(description = "政策文号，关联政策的官方文号，如“XX人社〔2025〕12号”，政策文件表（biz_public_pol）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "政策文号，关联政策的官方文号，如“XX人社〔2025〕12号”，政策文件表（biz_public_pol）不能为空")
    private String polNo;

    @Schema(description = "政策名称，与政策文号同步，政策文件表（biz_public_pol）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "政策名称，与政策文号同步，政策文件表（biz_public_pol）不能为空")
    private String polName;

    @Schema(description = "解读内容，分点拆解政策，支持图文", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解读内容，分点拆解政策，支持图文不能为空")
    private String interpretContent;

    @Schema(description = "发布部门编码，发布解读的部门编码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发布部门编码，发布解读的部门编码，部门信息表（sys_org）不能为空")
    private String publishDepartmentCode;

    @Schema(description = "发布部门名称，与部门编码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "发布部门名称，与部门编码同步，部门信息表（sys_org）不能为空")
    private String publishDepartmentName;

    @Schema(description = "发布人ID，发布人ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15521")
    @NotNull(message = "发布人ID，发布人ID，用户表（sys_user）不能为空")
    private Long publishUserId;

    @Schema(description = "发布时间，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发布时间，系统自动生成不能为空")
    private LocalDateTime publishTime;

    @Schema(description = "解读状态：1=草稿，2=已发布，3=已下架，控制展示状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "解读状态：1=草稿，2=已发布，3=已下架，控制展示状态不能为空")
    private Integer interpretStatus;

    @Schema(description = "生效时间，解读生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效时间，解读生效时间不能为空")
    private LocalDateTime effectiveTime;

    @Schema(description = "附件路径，政策原文附件路径")
    private String attachPath;

    @Schema(description = "备注，补充说明，如“配套XX政策使用”", example = "你猜")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识政策领域，如社保/人才")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识解读类型，如图文/视频")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储视频解读链接")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储政策原文链接")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}