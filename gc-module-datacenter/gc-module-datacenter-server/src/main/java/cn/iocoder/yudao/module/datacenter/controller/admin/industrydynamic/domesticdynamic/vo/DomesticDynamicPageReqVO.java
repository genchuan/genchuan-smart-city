package cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 国内行业动态分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DomesticDynamicPageReqVO extends PageParam {

    @Schema(description = "动态ID，唯一编码，UUID生成", example = "763")
    private String domesticDynamicId;

    @Schema(description = "动态标题，如“某城市一网统管平台上线”")
    private String dynamicTitle;

    @Schema(description = "动态标签，取值如平台建设/技术创新/政策动态，动态标签字典表（sys_dict_dynamic_tag）")
    private String dynamicTag;

    @Schema(description = "动态来源，动态来源平台（如“智慧城市网”）")
    private String dynamicSource;

    @Schema(description = "发布时间，格式：YYYYMMDD")
    private LocalDateTime[] releaseTime;

    @Schema(description = "动态详细内容（支持富文本）")
    private String dynamicDetail;

    @Schema(description = "封面图片ID，关联图片存储ID，文件存储表（sys_file_storage）", example = "6549")
    private String coverImgId;

    @Schema(description = "封面图片路径，封面图片存储路径，文件存储表（sys_file_storage）")
    private String coverImgPath;

    @Schema(description = "动态状态：已发布/草稿，标识动态是否公开", example = "2")
    private String dynamicStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“动态级别：国家级/省级”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“关联领域：城管/交通”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“原文链接”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“收藏数”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}