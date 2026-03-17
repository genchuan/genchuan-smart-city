package cn.iocoder.yudao.module.evaluate.dal.dataobject.timeaccessrule;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 实时接入规则 DO
 *
 * @author 亘川智城
 */
@TableName("real_time_access_rule")
@KeySequence("real_time_access_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeAccessRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则UUID
     */
    private String ruleId;
    /**
     * 规则名称
     */
    private String name;
    /**
     * 规则编码
     */
    private String code;
    /**
     * 关联评价任务ID
     */
    private String taskId;
    /**
     * 关联指标项ID
     */
    private String indexId;
    /**
     * 数据来源设备ID
     */
    private String deviceId;
    /**
     * 同步频率ID
     */
    private String syncFreqId;
    /**
     * 数据清洗规则
     */
    private String cleanRule;
    /**
     * 状态
     */
    private Long status;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 业务创建时间（原create_time）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 业务更新时间（原update_time）
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 最近同步时间
     */
    private LocalDateTime lastSyncTime;
    /**
     * 同步成功率
     */
    private BigDecimal syncSuccessRate;
    /**
     * 今日同步次数
     */
    private Integer todaySyncCount;
    /**
     * 累计同步次数
     */
    private Long totalSyncCount;
    /**
     * 停用原因
     */
    private String stopReason;
    /**
     * 停用时间
     */
    private LocalDateTime stopTime;
    /**
     * 停用操作人
     */
    private String stopBy;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}