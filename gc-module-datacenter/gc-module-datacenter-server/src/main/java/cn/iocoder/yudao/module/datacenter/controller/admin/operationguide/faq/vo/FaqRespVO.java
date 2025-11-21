package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 常见问题解答 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FaqRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30386")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "FAQ ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "32344")
    @ExcelProperty("FAQ ID，唯一编码，UUID生成")
    private String faqId;

    @Schema(description = "问题类型代码，01=系统操作问题，02=业务数据问题，03=流程执行问题，问题类型字典表（sys_dict_question_type）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题类型代码，01=系统操作问题，02=业务数据问题，03=流程执行问题，问题类型字典表（sys_dict_question_type）")
    private String questionTypeCode;

    @Schema(description = "问题类型名称，与问题类型代码同步，问题类型字典表（sys_dict_question_type）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("问题类型名称，与问题类型代码同步，问题类型字典表（sys_dict_question_type）")
    private String questionTypeName;

    @Schema(description = "问题描述，常见问题描述，如“地理编码坐标系配置失败怎么办？”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("问题描述，常见问题描述，如“地理编码坐标系配置失败怎么办？”")
    private String questionDesc;

    @Schema(description = "解答方案，问题解决方案，支持富文本，如“1.检查坐标系代码是否重复；2.确认坐标系名称含‘坐标系’关键词”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("解答方案，问题解决方案，支持富文本，如“1.检查坐标系代码是否重复；2.确认坐标系名称含‘坐标系’关键词”")
    private String answerSolution;

    @Schema(description = "关联指南ID，关联操作指南/业务指南ID，如通用操作指南ID、专项业务指南ID，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）", example = "6617")
    @ExcelProperty("关联指南ID，关联操作指南/业务指南ID，如通用操作指南ID、专项业务指南ID，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）")
    private String relatedGuideId;

    @Schema(description = "关联指南名称，与关联指南ID同步，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）", example = "张三")
    @ExcelProperty("关联指南名称，与关联指南ID同步，通用操作指南表（biz_general_oper_guide）/专项业务指南表（biz_spec_business_guide）")
    private String relatedGuideName;

    @Schema(description = "排序序号，1 - 999，FAQ列表展示排序优先级，数值越小越靠前", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("排序序号，1 - 999，FAQ列表展示排序优先级，数值越小越靠前")
    private Integer sortNum;

    @Schema(description = "是否置顶，0 = 否，1 = 是，标识FAQ是否置顶展示（仅10条可置顶）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否置顶，0 = 否，1 = 是，标识FAQ是否置顶展示（仅10条可置顶）")
    private String isTop;

    @Schema(description = "查看次数，用户查看FAQ的次数，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "10762")
    @ExcelProperty("查看次数，用户查看FAQ的次数，默认0")
    private Integer viewCount;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式yyyy - MM - dd HH:mm:ss，用户信息表（sys_user）")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“问题难度：简单/中等/复杂”")
    @ExcelProperty("分类扩展字段1，预留，如“问题难度：简单/中等/复杂”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“关联模块：地理编码/网格管理”")
    @ExcelProperty("分类扩展字段2，预留，如“关联模块：地理编码/网格管理”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“解决率：98%”")
    @ExcelProperty("通用扩展字段1，预留，如“解决率：98%”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“最后查看时间：2025 - 10 - 10”")
    @ExcelProperty("通用扩展字段2，预留，如“最后查看时间：2025 - 10 - 10”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}