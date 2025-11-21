package cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 国际行业动态新增/修改 Request VO")
@Data
public class InternationalDynamicSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8379")
    private Long id;

    @Schema(description = "动态ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "18998")
    @NotEmpty(message = "动态ID，唯一编码，UUID生成不能为空")
    private String internationalDynamicId;

    @Schema(description = "动态标题（中文），如“新加坡推出智慧国2.0计划”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "动态标题（中文），如“新加坡推出智慧国2.0计划”不能为空")
    private String dynamicTitle;

    @Schema(description = "英文标题，动态标题（英文）")
    private String dynamicTitleEn;

    @Schema(description = "来源国家地区，如“新加坡”“欧盟”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "来源国家地区，如“新加坡”“欧盟”不能为空")
    private String sourceCountry;

    @Schema(description = "动态来源，如“Smart City World”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "动态来源，如“Smart City World”不能为空")
    private String dynamicSource;

    @Schema(description = "发布时间，格式：YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发布时间，格式：YYYYMMDD不能为空")
    private LocalDateTime releaseTime;

    @Schema(description = "动态详细内容（中文，支持富文本）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "动态详细内容（中文，支持富文本）不能为空")
    private String dynamicDetail;

    @Schema(description = "英文详情，动态详细内容（英文）")
    private String dynamicDetailEn;

    @Schema(description = "封面图片ID，关联图片存储ID，文件存储表（sys_file_storage）", example = "31718")
    private String coverImgId;

    @Schema(description = "封面图片路径，封面图片存储路径，文件存储表（sys_file_storage）")
    private String coverImgPath;

    @Schema(description = "动态状态：已发布/草稿，标识动态是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "动态状态：已发布/草稿，标识动态是否公开不能为空")
    private String dynamicStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“动态类型：政策/技术”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“关联领域：数据安全/平台建设”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“原文链接（英文）”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“翻译人”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}