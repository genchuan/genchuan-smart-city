package cn.iocoder.yudao.module.system.dal.dataobject.riskcontrol;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 风险管控 DO
 *
 * @author zhucongquan
 */
@TableName("system_risk_control")
@KeySequence("system_risk_control_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskControlDO extends BaseDO {

    /**
     * 风险的唯一标识
     */
    @TableId
    private Integer id;
    /**
     * 风险名称
     */
    private String riskName;
    /**
     * 风险的详细描述
     */
    private String riskDescription;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 风险状态
     */
    private String riskStatus;
    /**
     * 记录的创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 记录的上次更新时间
     */
    private LocalDateTime updatedTime;

}