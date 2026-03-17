package cn.iocoder.yudao.module.evaluate.dal.dataobject.dockingrecord;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 系统对接记录 DO
 *
 * @author 亘川智城
 */
@TableName("sys_docking_record")
@KeySequence("sys_docking_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DockingRecordDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 对接UUID（主键，UUID）
     */
    private String dockingId;
    /**
     * 对接编号
     */
    private String code;
    /**
     * 外部系统ID（关联sys_external_system.system_id）
     */
    private String systemId;
    /**
     * 对接方式ID（关联sys_docking_type.type_id）
     */
    private String typeId;
    /**
     * 对接状态（关联sys_docking_status.status_id）
     */
    private String status;
    /**
     * 对接频率ID（关联sys_docking_frequency.frequency_id）
     */
    private String freqId;
    /**
     * 创建人（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 配置时间
     */
    private LocalDateTime configTime;
    /**
     * 最近对接时间
     */
    private LocalDateTime latestDockTime;
    /**
     * 对接成功率（%）
     */
    private BigDecimal successRate;
    /**
     * 累计同步数据量
     */
    private Long totalSyncNum;
    /**
     * 失败次数
     */
    private Integer failCount;
    /**
     * 最新失败原因
     */
    private String latestFailReason;
    /**
     * 数据映射规则摘要
     */
    private String mapRule;
    /**
     * 停用操作人（关联sys_user.user_id）
     */
    private String stopBy;
    /**
     * 停用时间
     */
    private LocalDateTime stopTime;
    /**
     * 停用原因
     */
    private String stopReason;
    /**
     * 停用时长（小时）
     */
    private BigDecimal stopHour;
    /**
     * 历史对接成功率（%）
     */
    private BigDecimal historySuccessRate;
    /**
     * 配置有效性校验结果（有效/无效）
     */
    private String configCheckResult;
    /**
     * 外部系统最新状态
     */
    private String externalStatus;
    /**
     * 重新对接次数
     */
    private Integer reDockCount;
    /**
     * 最新重新对接时间
     */
    private LocalDateTime latestReDockTime;
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