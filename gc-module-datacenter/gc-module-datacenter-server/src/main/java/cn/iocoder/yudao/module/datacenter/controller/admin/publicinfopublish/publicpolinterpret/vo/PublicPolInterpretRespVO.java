package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 政策解读发布 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicPolInterpretRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6285")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "解读ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "18009")
    @ExcelProperty("解读ID，唯一编码，UUID生成")
    private String interpretId;

    @Schema(description = "解读标题，含政策名称，如“2025人才补贴政策解读”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解读标题，含政策名称，如“2025人才补贴政策解读”")
    private String interpretTitle;

    @Schema(description = "政策文号，关联政策的官方文号，如“XX人社〔2025〕12号”，政策文件表（biz_public_pol）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("政策文号，关联政策的官方文号，如“XX人社〔2025〕12号”，政策文件表（biz_public_pol）")
    private String polNo;

    @Schema(description = "政策名称，与政策文号同步，政策文件表（biz_public_pol）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("政策名称，与政策文号同步，政策文件表（biz_public_pol）")
    private String polName;

    @Schema(description = "解读内容，分点拆解政策，支持图文", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解读内容，分点拆解政策，支持图文")
    private String interpretContent;

    @Schema(description = "发布部门编码，发布解读的部门编码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布部门编码，发布解读的部门编码，部门信息表（sys_org）")
    private String publishDepartmentCode;

    @Schema(description = "发布部门名称，与部门编码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("发布部门名称，与部门编码同步，部门信息表（sys_org）")
    private String publishDepartmentName;

    @Schema(description = "发布人ID，发布人ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "15521")
    @ExcelProperty("发布人ID，发布人ID，用户表（sys_user）")
    private Long publishUserId;

    @Schema(description = "发布时间，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布时间，系统自动生成")
    private LocalDateTime publishTime;

    @Schema(description = "解读状态：1=草稿，2=已发布，3=已下架，控制展示状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("解读状态：1=草稿，2=已发布，3=已下架，控制展示状态")
    private Integer interpretStatus;

    @Schema(description = "生效时间，解读生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生效时间，解读生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "附件路径，政策原文附件路径")
    @ExcelProperty("附件路径，政策原文附件路径")
    private String attachPath;

    @Schema(description = "备注，补充说明，如“配套XX政策使用”", example = "你猜")
    @ExcelProperty("备注，补充说明，如“配套XX政策使用”")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识政策领域，如社保/人才")
    @ExcelProperty("分类扩展字段1，预留，标识政策领域，如社保/人才")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识解读类型，如图文/视频")
    @ExcelProperty("分类扩展字段2，预留，标识解读类型，如图文/视频")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储视频解读链接")
    @ExcelProperty("通用扩展字段1，预留，存储视频解读链接")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储政策原文链接")
    @ExcelProperty("通用扩展字段2，预留，存储政策原文链接")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}