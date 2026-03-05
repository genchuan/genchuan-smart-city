package cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeetemp;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 临停收费规则 DO
 *
 * @author lxs
 */
@TableName("park_fee_temp")
@KeySequence("park_fee_temp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkFeeTempDO extends BaseDO {

    /**
     * [主键ID] 临停收费规则唯一标识
     */
    @TableId
    private Long id;
    /**
     * [规则名称] 临停收费规则名称
     */
    private String ruleName;
    /**
     * [适用车场ID列表] varchar，存储车场ID集合
     */
    private String lotIds;
    /**
     * [费率策略ID] 关联费率策略 park_fee_strategy.id
     */
    private Long feeStrategyId;
    /**
     * [免费停放时长] 单位：分钟
     */
    private Integer freeParkingTime;
    /**
     * [单日最高费用] 超过该金额后封顶
     */
    private BigDecimal maxDailyFee;
    /**
     * [状态] 启用 / 禁用
     */
    private String status;
    /**
     * [备注] 临停收费规则相关说明
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
