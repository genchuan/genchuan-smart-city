package cn.iocoder.yudao.module.smartcity.dal.dataobject.diagnosiswarning;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 故障诊断和预警 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_diagnosis_warning")
@KeySequence("smartcity_diagnosis_warning_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisWarningDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 故障设备名称
     */
    private String nameFaultyEquipment;
    /**
     * 故障设备编号
     */
    private String faultEquipmentNumber;
    /**
     * 故障发生时间
     */
    private String timeFailure;
    /**
     * 故障现象描述
     */
    private String descriptionSymptoms;
    /**
     * 故障可能原因分析
     */
    private String analysisPossibleMalfunction;
    /**
     * 诊断方法与依据
     */
    private String diagnosticMethodsBasis;
    /**
     * 预警级别
     */
    private String warningLevel;
    /**
     * 预警时间
     */
    private String warningTime;
    /**
     * 预警信息内容
     */
    private String warningInformationContent;
    /**
     * 专家评估意见
     */
    private String expertEvaluationOpinions;
    /**
     * 系统恢复时间
     */
    private String systemRecoveryTime;
    /**
     * 故障对业务的影响程度
     */
    private String theDegreeBusiness;
    /**
     * 后续预防措施
     */
    private String subsequentPreventiveMeasures;
    /**
     * 备用设备投入情况
     */
    private String backupDeploymentStatus;

}