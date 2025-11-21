package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 常见问题解答分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FaqPageReqVO extends PageParam {

    @Schema(description = "FAQ ID，唯一编码，UUID生成", example = "32344")
    private String faqId;

    @Schema(description = "问题类型代码，01=系统操作问题，02=业务数据问题，03=流程执行问题，问题类型字典表（sys_dict_question_type）")
    private String questionTypeCode;

    @Schema(description = "问题类型名称，与问题类型代码同步，问题类型字典表（sys_dict_question_type）", example = "赵六")
    private String questionTypeName;

    @Schema(description = "问题描述，常见问题描述，如“地理编码坐标系配置失败怎么办？”")
    private String questionDesc;

    @Schema(description = "解答方案，问题解决方案，支持富文本，如“1.检查坐标系代码是否重复；2.确认坐标系名称含‘坐标系’关键词”")
    private String answerSolution;

    @Schema(description = "关联指南ID，关联操作指南/业务指南ID，如通用操作指南ID、专项业务指南ID，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）", example = "6617")
    private String relatedGuideId;

    @Schema(description = "关联指南名称，与关联指南ID同步，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）", example = "张三")
    private String relatedGuideName;

    @Schema(description = "排序序号，1 - 999，FAQ列表展示排序优先级，数值越小越靠前")
    private Integer sortNum;

    @Schema(description = "是否置顶，0 = 否，1 = 是，标识FAQ是否置顶展示（仅10条可置顶）")
    private String isTop;

    @Schema(description = "查看次数，用户查看FAQ的次数，默认0", example = "10762")
    private Integer viewCount;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，用户信息表（sys_user）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“问题难度：简单/中等/复杂”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“关联模块：地理编码/网格管理”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“解决率：98%”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“最后查看时间：2025 - 10 - 10”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}