package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 政策解读发布分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicPolInterpretPageReqVO extends PageParam {

    @Schema(description = "解读ID，唯一编码，UUID生成", example = "18009")
    private String interpretId;

    @Schema(description = "解读标题，含政策名称，如“2025人才补贴政策解读”")
    private String interpretTitle;

    @Schema(description = "政策文号，关联政策的官方文号，如“XX人社〔2025〕12号”，政策文件表（biz_public_pol）")
    private String polNo;

    @Schema(description = "政策名称，与政策文号同步，政策文件表（biz_public_pol）", example = "赵六")
    private String polName;

    @Schema(description = "解读内容，分点拆解政策，支持图文")
    private String interpretContent;

    @Schema(description = "发布部门编码，发布解读的部门编码，部门信息表（sys_org）")
    private String publishDepartmentCode;

    @Schema(description = "发布部门名称，与部门编码同步，部门信息表（sys_org）", example = "王五")
    private String publishDepartmentName;

    @Schema(description = "发布人ID，发布人ID，用户表（sys_user）", example = "15521")
    private Long publishUserId;

    @Schema(description = "发布时间，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] publishTime;

    @Schema(description = "解读状态：1=草稿，2=已发布，3=已下架，控制展示状态", example = "1")
    private Integer interpretStatus;

    @Schema(description = "生效时间，解读生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

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