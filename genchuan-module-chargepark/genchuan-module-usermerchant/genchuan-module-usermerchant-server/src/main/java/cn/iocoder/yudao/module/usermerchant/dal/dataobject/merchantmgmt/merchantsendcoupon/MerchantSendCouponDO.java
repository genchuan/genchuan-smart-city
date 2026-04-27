package cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantsendcoupon;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商户发券 DO
 *
 * @author 亘川智城
 */
@TableName("merchant_send_coupon")
@KeySequence("merchant_send_coupon_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantSendCouponDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 商户ID，关联merchant_info.id
     */
    private Long merchantId;
    /**
     * 优惠券ID，关联营销模块优惠券表
     */
    private Long couponId;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 发放数量
     */
    private Integer sendCount;
    /**
     * 执行时间
     */
    private LocalDateTime execTime;
    /**
     * 发券完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 已核销数量
     */
    private Integer useCount;
    /**
     * 发券状态：待执行/已执行/已取消
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}