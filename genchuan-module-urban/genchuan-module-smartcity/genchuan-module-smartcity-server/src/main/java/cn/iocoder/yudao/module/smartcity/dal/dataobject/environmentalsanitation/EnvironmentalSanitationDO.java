package cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsanitation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 市容环卫 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_environmental_sanitation")
@KeySequence("smartcity_environmental_sanitation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentalSanitationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 设施名称
     */
    private String facilityName;
    /**
     * 设施位置
     */
    private String facilityLocation;
    /**
     * 数量
     */
    private String quantity;
    /**
     * 建设时间
     */
    private LocalDateTime constructionTime;
    /**
     * 使用年限
     */
    private String serviceLife;
    /**
     * 处理能力
     */
    private String processingCapacity;
    /**
     * 维护需求
     */
    private String maintenanceRequirements;
    /**
     * 年度预算金额
     */
    private String annualBudgetAmount;
    /**
     * 成本效益分析
     */
    private String costBenefitAnalysis;
    /**
     * 对周边环境影响评估
     */
    private String assessmentSurEnvironment;
    /**
     * 节能减排指标
     */
    private String energyConservationTargets;

}