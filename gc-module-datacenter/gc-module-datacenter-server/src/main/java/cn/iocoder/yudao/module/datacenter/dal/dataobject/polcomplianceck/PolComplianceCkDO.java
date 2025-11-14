package cn.iocoder.yudao.module.datacenter.dal.dataobject.polcomplianceck;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 政策合规校验 DO
 *
 * @author 亘川智城
 */
@TableName("biz_pol_compliance_ck")
@KeySequence("biz_pol_compliance_ck_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolComplianceCkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 校验ID，唯一编码，UUID生成
     */
    private String complianceCkId;
    /**
     * 关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）
     */
    private String relSchemeId;
    /**
     * 关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）
     */
    private String relSchemeName;
    /**
     * 校验政策IDs，关联政策法规库表的政策ID，逗号分隔，政策法规库表（sys_pol_library）
     */
    private String ckPolIds;
    /**
     * 校验政策名称，与政策IDs同步，逗号分隔，政策法规库表（sys_pol_library）
     */
    private String ckPolNames;
    /**
     * 校验状态：待校验/校验中/已通过/存在风险，标识校验进度
     */
    private String ckStatus;
    /**
     * 风险等级：无/低/中/高，校验状态为“存在风险”时必填
     */
    private String riskLevel;
    /**
     * 风险点数量，个，校验出的风险点总数，合规校验结果表（biz_compliance_ck_result）
     */
    private Integer riskPtCount;
    /**
     * 合规率，%，(校验项总数-风险点数量)/校验项总数×100，0.00-100.00
     */
    private BigDecimal complianceRate;
    /**
     * 校验结论，如“方案存在2个中风险点，需整改后实施”
     */
    private String ckConclusion;
    /**
     * 校验人，发起校验的用户ID，用户信息表（sys_user）
     */
    private String ckUser;
    /**
     * 校验时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime ckTime;
    /**
     * 整改建议，针对风险点的整改方向（如“设备高度降至8米以内，补充安全评估报告”）
     */
    private String rectificationSugg;
    /**
     * 复核状态：待复核/复核通过/复核驳回，风险整改后复核状态
     */
    private String reviewStatus;
    /**
     * 复核人，复核用户ID，复核状态非“待复核”时必填，用户信息表（sys_user）
     */
    private String reviewUser;
    /**
     * 复核时间，格式：yyyy-MM-dd HH:mm:ss，复核状态非“待复核”时必填
     */
    private LocalDateTime reviewTime;
    /**
     * 分类扩展字段1，预留，如“校验批次”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“校验批次”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储政策版本号
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储政策版本号
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