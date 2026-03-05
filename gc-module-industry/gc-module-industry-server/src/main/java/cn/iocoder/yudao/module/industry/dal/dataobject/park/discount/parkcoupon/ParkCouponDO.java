package cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkcoupon;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 优惠券 DO
 *
 * @author lxs
 */
@TableName("park_coupon")
@KeySequence("park_coupon_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkCouponDO extends BaseDO {

    /**
     * [主键ID] 优惠券唯一标识
     */
    @TableId
    private Long id;
    /**
     * [优惠券码] 全局唯一优惠券码
     */
    private String couponCode;
    /**
     * [优惠券名称] 优惠券名称
     */
    private String couponName;
    /**
     * [优惠券类型] 满减券 / 折扣券 / 免费时长券 / 充值券
     */
    private String couponType;
    /**
     * [适用范围类型] 全局/车场
     */
    private String applyScopeType;
    /**
     * [适用范围值] 车场ID列表，英文逗号分隔，如1,2
     */
    private String applyScopeValue;
    /**
     * [面值/折扣比例] 优惠券面值或折扣比例
     */
    private BigDecimal faceValue;
    /**
     * [最低消费金额] 仅满减券 / 折扣券适用
     */
    private BigDecimal minConsume;
    /**
     * [免费时长] 单位：分钟，仅免费时长券适用
     */
    private Integer freeTime;
    /**
     * [生效时间] 优惠券生效时间
     */
    private LocalDateTime startTime;
    /**
     * [失效时间] 优惠券失效时间
     */
    private LocalDateTime endTime;
    /**
     * [状态] 未发放 / 已发放 / 已使用 / 已过期 / 已作废
     */
    private String status;
    /**
     * [用户ID] 定向发放用户，关联 park_user.id
     */
    private Long userId;
    /**
     * [活动ID] 关联 park_promotion.promotion_id
     */
    private Long promotionId;
    /**
     * [领取时间] 优惠券领取时间
     */
    private LocalDateTime getTime;
    /**
     * [使用时间] 优惠券使用时间
     */
    private LocalDateTime useTime;
    /**
     * [使用订单ID] 关联订单ID
     */
    private Long useOrderId;
    /**
     * [备注] 优惠券相关备注说明
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
