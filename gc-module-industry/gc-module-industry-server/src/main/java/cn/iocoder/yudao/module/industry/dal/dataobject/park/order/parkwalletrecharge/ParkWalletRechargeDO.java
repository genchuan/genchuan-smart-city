package cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkwalletrecharge;

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
 * 钱包充值 DO
 *
 * @author lxs
 */
@TableName("park_wallet_recharge")
@KeySequence("park_wallet_recharge_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkWalletRechargeDO extends BaseDO {

    /**
     * 主键ID，自增
     */
    @TableId
    private Long id;
    /**
     * 充值单号，唯一充值单号
     */
    private String rechargeNo;
    /**
     * 用户ID，关联 sys_user.id
     */
    private Long userId;
    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;
    /**
     * 实付金额，用户实际支付金额
     */
    private BigDecimal actualPayAmount;
    /**
     * 充值套餐ID，关联 park_recharge_package.package_id，可为 NULL
     */
    private Long rechargePackageId;
    /**
     * 优惠金额，充值优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 支付方式：微信/支付宝/银行卡/其他
     */
    private String payWay;
    /**
     * 充值状态：待支付 / 已支付 / 已取消 / 支付失败
     */
    private String rechargeStatus;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 到账时间，充值到账时间
     */
    private LocalDateTime rechargeTime;
    /**
     * 优惠券ID，关联 park_coupon.coupon_id，可为 NULL
     */
    private Long couponId;
    /**
     * 备注，业务备注说明
     */
    private String remark;
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
