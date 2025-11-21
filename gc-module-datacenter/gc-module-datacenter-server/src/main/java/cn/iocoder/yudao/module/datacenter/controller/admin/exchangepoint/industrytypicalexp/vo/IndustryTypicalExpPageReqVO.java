package cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.industrytypicalexp.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 行业典型经验分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IndustryTypicalExpPageReqVO extends PageParam {

    @Schema(description = "经验ID，唯一编码，UUID生成", example = "9909")
    private String industryTypicalExpId;

    @Schema(description = "经验标题，如“智慧城管事件处置效率提升经验”")
    private String expTitle;

    @Schema(description = "行业领域代码，取值如01=城管，02=环保，03=交通，行业领域字典表（sys_dict_industry_field）")
    private String industryFieldCode;

    @Schema(description = "行业领域名称，与行业领域代码同步，行业领域字典表（sys_dict_industry_field）", example = "李四")
    private String industryFieldName;

    @Schema(description = "案例单位，经验来源案例单位，如“XX市城管执法局”，部门信息表（sys_org）")
    private String caseOrg;

    @Schema(description = "经验核心亮点，支持富文本，如“1.引入AI分派：自动匹配处置部门；2.建立台账：跟踪处置进度”")
    private String expHighlights;

    @Schema(description = "经验实施效果，如“事件处置时长从24小时缩短至8小时，效率提升66%”")
    private String implementationEffect;

    @Schema(description = "附件ID，关联经验附件ID（如案例报告、效果对比图），文件存储表（sys_file_storage）", example = "32214")
    private String attachId;

    @Schema(description = "附件存储路径，文件存储表（sys_file_storage）")
    private String attachPath;

    @Schema(description = "发布状态：已发布/草稿，标识经验是否公开", example = "2")
    private String publishStatus;

    @Schema(description = "点赞次数，用户点赞次数，默认0", example = "22590")
    private Integer likeCount;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“经验类型：技术优化/流程改进”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用规模：市级/区级”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“实施周期：3个月”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“最后点赞时间：2025-10-10”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}