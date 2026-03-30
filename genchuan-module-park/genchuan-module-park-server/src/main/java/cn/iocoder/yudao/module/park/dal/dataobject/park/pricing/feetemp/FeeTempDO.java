package cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feetemp;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 临停收费规则 DO
 *
 * @author 亘川智城
 */
@TableName("park_fee_temp")
@KeySequence("park_fee_temp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeeTempDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [规则名称]
     */
    private String ruleName;
    /**
     * [费率策略ID] 关联park_fee_strategy.id
     */
    private Long feeStrategyId;
    /**
     * [免费停放时长] 分钟
     */
    private Integer freeParkingTime;
    /**
     * [单日最高费用] 优先级高于费率策略
     */
    private BigDecimal maxDailyFee;
    /**
     * [适用车场ID列表] JSON格式varchar，关联park_lot.id
     */
    private String applyLotIds;
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
