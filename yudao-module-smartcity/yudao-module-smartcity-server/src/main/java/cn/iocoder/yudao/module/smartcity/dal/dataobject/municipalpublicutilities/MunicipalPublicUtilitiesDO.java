package cn.iocoder.yudao.module.smartcity.dal.dataobject.municipalpublicutilities;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 市政公用 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_municipal_public_utilities")
@KeySequence("smartcity_municipal_public_utilities_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalPublicUtilitiesDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 市政设施名称
     */
    private String nameMunicipalFacilities;
    /**
     * 设施类型
     */
    private String facilityType;
    /**
     * 设施位置
     */
    private String facilityLocation;
    /**
     * 建设时间
     */
    private LocalDateTime constructionTime;
    /**
     * 使用年限
     */
    private String serviceLife;
    /**
     * 承载能力
     */
    private String bearingCapacity;
    /**
     * 服务范围
     */
    private String serviceScope;
    /**
     * 服务人口
     */
    private String servicePopulation;
    /**
     * 资源配置
     */
    private String resourceAllocation;
    /**
     * 资金投入
     */
    private String capitalInvestment;
    /**
     * 预算安排
     */
    private String budgetArrangement;
    /**
     * 效益评估
     */
    private String benefitsAssessment;
    /**
     * 政策法规依据
     */
    private String policyRegulatoryBasis;
    /**
     * 行业标准规范
     */
    private String industryStandardSpecifications;
    /**
     * 安全隐患
     */
    private String potentialSafetyHazard;
    /**
     * 风险评估
     */
    private String riskAssessment;
    /**
     * 应急处置预案
     */
    private String emergencyResponsePlan;

}