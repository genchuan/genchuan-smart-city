package cn.iocoder.yudao.module.park.dal.dataobject.park.trade.walletflow;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 钱包流水 DO
 *
 * @author 亘川智城
 */
@TableName("wallet_flow")
@KeySequence("wallet_flow_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletFlowDO extends BaseDO {

    /**
     * [主键ID] 钱包流水记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [钱包ID] 钱包唯一标识，关联 user_wallet.id
     */
    private Long walletId;
    /**
     * [用户ID] 用户唯一标识，关联 park_user.id
     */
    private Long userId;
    /**
     * [业务交易号] 业务交易唯一编号
     */
    private String tradeCode;
    /**
     * [交易完成时间] 交易完成时间
     */
    private LocalDateTime tradeFinishTime;
    /**
     * [变动金额] 变动金额（正=收入 负=支出）
     */
    private BigDecimal amount;
    /**
     * [变动后余额] 变动后余额
     */
    private BigDecimal balanceAfter;
    /**
     * [流水描述] 流水描述，如：支付临停订单，扣费XX
     */
    private String flowDesc;
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
