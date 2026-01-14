package cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkchargefee;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充电收费 DO
 *
 * @author lxs
 */
@TableName("park_charge_fee")
@KeySequence("park_charge_fee_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkChargeFeeDO extends BaseDO {

    /**
     * [主键ID] 充电收费记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [费率策略ID] 关联 park_fee_strategy.id
     */
    private Long feeStrategyId;
    /**
     * [充电桩ID] 关联 tb_device_extend.device_extend_id
     */
    private Long chargePileId;
    /**
     * [基础充电费率] 元/度
     */
    private BigDecimal baseRate;
    /**
     * [高峰充电费率] 元/度
     */
    private BigDecimal peakRate;
    /**
     * [平峰充电费率] 元/度
     */
    private BigDecimal offPeakRate;
    /**
     * [是否合并停车费] 如:0-否/1-是
     */
    private Boolean mergeParkingFee;
    /**
     * [活动ID] 关联 park_promotion.promotion_id，充电活动
     */
    private Long activityId;
    /**
     * [状态] 如:启用/禁用
     */
    private String status;
    /**
     * [备注] 充电收费相关备注说明
     */
    private String remark;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}
