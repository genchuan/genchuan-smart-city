package cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeestrategy;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 费率策略 DO
 *
 * @author lxs
 */
@TableName("park_fee_strategy")
@KeySequence("park_fee_strategy_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkFeeStrategyDO extends BaseDO {

    /**
     * [主键ID] 费率策略唯一标识
     */
    @TableId
    private Long id;
    /**
     * [策略名称] 费率策略名称
     */
    private String strategyName;
    /**
     * [策略类型] 基础费率 / 时段费率 / 区域费率
     */
    private String strategyType;
    /**
     * [适用范围] 全局 / 区域 / 车场，JSON 格式varchar
     */
    private String applyScope;
    /**
     * [基础费率] 元/分钟
     */
    private BigDecimal baseRate;
    /**
     * [高峰费率] 元/分钟，仅时段费率策略适用
     */
    private BigDecimal peakRate;
    /**
     * [平峰费率] 元/分钟，仅时段费率策略适用
     */
    private BigDecimal offPeakRate;
    /**
     * [区域费率配置] JSON 格式，仅区域费率策略适用
     */
    private String regionRate;
    /**
     * [生效时间] 策略生效时间
     */
    private LocalDateTime startTime;
    /**
     * [失效时间] 策略失效时间，永久有效为 NULL
     */
    private LocalDateTime endTime;
    /**
     * [状态] 启用 / 禁用
     */
    private String status;
    /**
     * [备注] 费率策略相关备注说明
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
