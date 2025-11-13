package cn.iocoder.yudao.module.datacenter.controller.admin.decisionscheme.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 决策方案生成 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DecisionSchemeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18899")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "方案ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "20912")
    @ExcelProperty("方案ID，唯一编码，UUID生成")
    private String decisionSchemeId;

    @Schema(description = "方案名称，如“XX区域燃气泄漏事件处置优化方案”", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("方案名称，如“XX区域燃气泄漏事件处置优化方案”")
    private String schemeName;

    @Schema(description = "关联分析ID，关联分域分析统计ID，分域分析统计表（如stat_analysis_mon_evt）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8613")
    @ExcelProperty("关联分析ID，关联分域分析统计ID，分域分析统计表（如stat_analysis_mon_evt）")
    private String relAnalysisId;

    @Schema(description = "关联分析类型，按行政区划/按监测事件/按设备，标识关联分析的分域类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("关联分析类型，按行政区划/按监测事件/按设备，标识关联分析的分域类型")
    private String relAnalysisType;

    @Schema(description = "方案目标，如“3个月内该区域燃气泄漏事件下降50%”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("方案目标，如“3个月内该区域燃气泄漏事件下降50%”")
    private String schemeGoal;

    @Schema(description = "实施措施，分点描述措施（如“1.新增3个传感器；2.组建专项小组”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("实施措施，分点描述措施（如“1.新增3个传感器；2.组建专项小组”）")
    private String implementationMeasures;

    @Schema(description = "资源需求，如“传感器3台（预算5万元）、人员5名”，资源信息表（biz_res_info）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源需求，如“传感器3台（预算5万元）、人员5名”，资源信息表（biz_res_info）")
    private String resDemand;

    @Schema(description = "预期效果，如“事件响应时长缩短至20分钟内”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预期效果，如“事件响应时长缩短至20分钟内”")
    private String expectedEffect;

    @Schema(description = "方案状态：待审核/已通过/已驳回/已生效/已终止，标识方案生命周期状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("方案状态：待审核/已通过/已驳回/已生效/已终止，标识方案生命周期状态")
    private String schemeStatus;

    @Schema(description = "申请人ID，提交方案的分析师ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "513")
    @ExcelProperty("申请人ID，提交方案的分析师ID，用户信息表（sys_user）")
    private String applyUserId;

    @Schema(description = "申请人姓名，与申请人ID同步，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("申请人姓名，与申请人ID同步，用户信息表（sys_user）")
    private String applyUserName;

    @Schema(description = "申请时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;

    @Schema(description = "审核人ID，审核方案的审核员ID，状态为“已通过/已驳回”时必填，用户信息表（sys_user）", example = "31639")
    @ExcelProperty("审核人ID，审核方案的审核员ID，状态为“已通过/已驳回”时必填，用户信息表（sys_user）")
    private String auditUserId;

    @Schema(description = "审核人姓名，与审核人ID同步，用户信息表（sys_user）", example = "张三")
    @ExcelProperty("审核人姓名，与审核人ID同步，用户信息表（sys_user）")
    private String auditUserName;

    @Schema(description = "审核时间，格式：yyyy-MM-dd HH:mm:ss，状态为“已通过/已驳回”时必填")
    @ExcelProperty("审核时间，格式：yyyy-MM-dd HH:mm:ss，状态为“已通过/已驳回”时必填")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见，审核反馈（如“同意方案，建议增加预算至6万元”）")
    @ExcelProperty("审核意见，审核反馈（如“同意方案，建议增加预算至6万元”）")
    private String auditOpinion;

    @Schema(description = "分类扩展字段1，预留，如“方案优先级”")
    @ExcelProperty("分类扩展字段1，预留，如“方案优先级”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“方案优先级”")
    @ExcelProperty("分类扩展字段2，预留，如“方案优先级”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储方案实施周期")
    @ExcelProperty("通用扩展字段1，预留，存储方案实施周期")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储方案实施周期")
    @ExcelProperty("通用扩展字段2，预留，存储方案实施周期")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}