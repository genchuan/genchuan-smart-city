package cn.iocoder.yudao.module.park.dal.dataobject.park.trade.wallet;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 用户钱包 DO
 *
 * @author 亘川智城
 */
@TableName("park_wallet")
@KeySequence("park_wallet_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDO extends BaseDO {

    /**
     * [主键ID] 用户钱包唯一标识
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 用户唯一标识
     */
    private Long userId;
    /**
     * [当前余额] 用户钱包当前余额
     */
    private BigDecimal balance;
    /**
     * [状态] 如:冻结/正常
     */
    private String status;
    /**
     * [版本号] 版本号(乐观锁)
     */
    private Integer version;
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
