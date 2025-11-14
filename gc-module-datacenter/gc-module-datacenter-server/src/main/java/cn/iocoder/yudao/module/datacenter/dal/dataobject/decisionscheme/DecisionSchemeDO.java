package cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionscheme;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 决策方案生成 DO
 *
 * @author 亘川智城
 */
@TableName("biz_decision_scheme")
@KeySequence("biz_decision_scheme_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecisionSchemeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 方案ID，唯一编码，UUID生成
     */
    private String decisionSchemeId;
    /**
     * 方案名称，如“XX区域燃气泄漏事件处置优化方案”
     */
    private String schemeName;
    /**
     * 关联分析ID，关联分域分析统计ID，分域分析统计表（如stat_analysis_mon_evt）
     */
    private String relAnalysisId;
    /**
     * 关联分析类型，按行政区划/按监测事件/按设备，标识关联分析的分域类型
     */
    private String relAnalysisType;
    /**
     * 方案目标，如“3个月内该区域燃气泄漏事件下降50%”
     */
    private String schemeGoal;
    /**
     * 实施措施，分点描述措施（如“1.新增3个传感器；2.组建专项小组”）
     */
    private String implementationMeasures;
    /**
     * 资源需求，如“传感器3台（预算5万元）、人员5名”，资源信息表（biz_res_info）
     */
    private String resDemand;
    /**
     * 预期效果，如“事件响应时长缩短至20分钟内”
     */
    private String expectedEffect;
    /**
     * 方案状态：待审核/已通过/已驳回/已生效/已终止，标识方案生命周期状态
     */
    private String schemeStatus;
    /**
     * 申请人ID，提交方案的分析师ID，用户信息表（sys_user）
     */
    private String applyUserId;
    /**
     * 申请人姓名，与申请人ID同步，用户信息表（sys_user）
     */
    private String applyUserName;
    /**
     * 申请时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime applyTime;
    /**
     * 审核人ID，审核方案的审核员ID，状态为“已通过/已驳回”时必填，用户信息表（sys_user）
     */
    private String auditUserId;
    /**
     * 审核人姓名，与审核人ID同步，用户信息表（sys_user）
     */
    private String auditUserName;
    /**
     * 审核时间，格式：yyyy-MM-dd HH:mm:ss，状态为“已通过/已驳回”时必填
     */
    private LocalDateTime auditTime;
    /**
     * 审核意见，审核反馈（如“同意方案，建议增加预算至6万元”）
     */
    private String auditOpinion;
    /**
     * 分类扩展字段1，预留，如“方案优先级”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“方案优先级”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储方案实施周期
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储方案实施周期
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}