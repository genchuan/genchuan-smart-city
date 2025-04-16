package cn.iocoder.yudao.module.smartcity.dal.dataobject.bridgesafety;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 桥梁安全 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_bridge_safety")
@KeySequence("smartcity_bridge_safety_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BridgeSafetyDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 桥梁名称
     */
    private String bridgeName;
    /**
     * 桥梁编号
     */
    private String bridgeNumber;
    /**
     * 桥梁位置
     */
    private String bridgeLocation;
    /**
     * 桥梁类型
     */
    private String bridgeType;
    /**
     * 建造时间
     */
    private LocalDateTime constructionTime;
    /**
     * 设计荷载
     */
    private String designLoad;
    /**
     * 结构部件状况
     */
    private String conditionStructuralComponents;
    /**
     * 基础沉降数据
     */
    private String basicSettlementData;
    /**
     * 索力
     */
    private String cableForce;
    /**
     * 振动频率
     */
    private String vibrationFrequency;
    /**
     * 位移监测数据
     */
    private String displacementMonitoringData;
    /**
     * 伸缩缝状态
     */
    private String expansionJointStatus;
    /**
     * 桥面铺装状况
     */
    private String bridgeDeckPavementCondition;
    /**
     * 附属设施状况
     */
    private String conditionAncillaryFacilities;
    /**
     * 地震监测数据
     */
    private String seismicMonitoringData;
    /**
     * 冲刷情况
     */
    private String erosionSituation;
    /**
     * 水质情况
     */
    private String waterQualitySituation;
    /**
     * 专家评估意见
     */
    private String expertEvaluationOpinions;

}