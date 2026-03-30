package cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feestrategy;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 费率策略 DO
 *
 * @author 亘川智城
 */
@TableName("park_fee_strategy")
@KeySequence("park_fee_strategy_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeeStrategyDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [策略名称]
     */
    private String strategyName;
    /**
     * [基础费率] 元/分钟
     */
    private BigDecimal baseRate;
    /**
     * [单日最高费用]
     */
    private BigDecimal maxDailyFee;
    /**
     * [适用范围] 如:全局/区域/车场
     */
    private String applyScope;
    /**
     * [适用范围ID列表] JSON格式varchar，区域ID/车场ID
     */
    private String scopeIds;
    /**
     * [高峰时段] JSON格式varchar
     */
    private String peakTime;
    /**
     * [高峰费率] 元/分钟
     */
    private BigDecimal peakRate;
    /**
     * [平峰时段] JSON格式varchar
     */
    private String offPeakTime;
    /**
     * [平峰费率] 元/分钟
     */
    private BigDecimal offPeakRate;
    /**
     * [生效时间]
     */
    private LocalDateTime effectTime;
    /**
     * [失效时间]
     */
    private LocalDateTime expireTime;
    /**
     * [状态] 如:启用/禁用
     */
    private String status;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}
