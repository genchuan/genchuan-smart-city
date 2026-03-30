package cn.iocoder.yudao.module.park.dal.dataobject.park.order.settlement;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 分账结算 DO
 *
 * @author 亘川智城
 */
@TableName("park_settlement")
@KeySequence("park_settlement_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementDO extends BaseDO {

    /**
     * [主键ID] 分账结算唯一标识
     */
    @TableId
    private Long id;
    /**
     * [商户ID] 关联商户ID，park_merchant.id
     */
    private Long merchantId;
    /**
     * [车场ID] 关联车场ID，park_lot.id
     */
    private Long lotId;
    /**
     * [统计周期] 日/周/月
     */
    private String statCycle;
    /**
     * [统计开始日期] 结算统计开始日期
     */
    private LocalDate startDate;
    /**
     * [统计结束日期] 结算统计结束日期
     */
    private LocalDate endDate;
    /**
     * [总交易额] 统计期间总交易额
     */
    private BigDecimal totalAmount;
    /**
     * [退款金额] 统计期间退款金额
     */
    private BigDecimal refundAmount;
    /**
     * [平台分成金额] 平台分成金额
     */
    private BigDecimal platformAmount;
    /**
     * [商户分成金额] 商户分成金额
     */
    private BigDecimal merchantAmount;
    /**
     * [税费金额] 税费金额
     */
    private BigDecimal taxAmount;
    /**
     * [结算状态] 待审核/已审核/已支付/已驳回
     */
    private String settlementStatus;
    /**
     * [支付时间] 支付时间，可为 NULL
     */
    private LocalDateTime payTime;
    /**
     * [支付方式] 支付方式
     */
    private String payType;
    /**
     * [备注] 分账结算相关备注说明
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
