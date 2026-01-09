package cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsafety;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 环卫设施安全 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_environmental_safety")
@KeySequence("smartcity_environmental_safety_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentalSafetyDO extends BaseDO {

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
     * 设施类型
     */
    private String facilityType;
    /**
     * 设施位置
     */
    private String facilityLocation;
    /**
     * 设施编号
     */
    private String facilityNumber;
    /**
     * 运行状态
     */
    private String runningState;
    /**
     * 故障代码
     */
    private String faultCode;
    /**
     * 维修记录
     */
    private String maintenanceRecord;
    /**
     * 维修时间
     */
    private String maintenanceTime;
    /**
     * 异味浓度
     */
    private String odorConcentration;
    /**
     * 有害气体含量
     */
    private String harmfulGasContent;
    /**
     * 消毒记录
     */
    private String disinfectionRecord;
    /**
     * 蝇虫密度
     */
    private String flyAndInsectDensity;
    /**
     * 设施使用频率
     */
    private String facilityUsageFrequency;
    /**
     * 投诉记录
     */
    private String complaintRecord;

}