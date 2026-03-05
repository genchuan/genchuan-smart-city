package cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrule;

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
 * 积分规则 DO
 *
 * @author lxs
 */
@TableName("park_points_rule")
@KeySequence("park_points_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPointsRuleDO extends BaseDO {

    /**
     * [主键ID] 积分规则唯一标识
     */
    @TableId
    private Long id;
    /**
     * [规则名称] 积分规则名称
     */
    private String ruleName;
    /**
     * [触发类型] 如:停车消费/充值/分享/投诉反馈/会员任务
     */
    private String triggerType;
    /**
     * [固定积分值] 固定赠送的积分值
     */
    private BigDecimal pointsAmount;
    /**
     * [积分比例] 积分计算比例，0~1 小数
     */
    private BigDecimal pointsRatio;
    /**
     * [单日上限] 单条规则单日可获得的积分上限
     */
    private BigDecimal upperLimit;
    /**
     * [状态] 如:禁用/启用
     */
    private String status;
    /**
     * [备注] 积分规则相关备注说明
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
