package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkdiscountactivity;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 优惠活动 DO
 *
 * @author lxs
 */
@TableName("biz_park_discount_activity")
@KeySequence("biz_park_discount_activity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkDiscountActivityDO extends BaseDO {

    /**
     * 主键，记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * 优惠活动唯一编号
     */
    private String discountActivityNo;
    /**
     * 优惠活动名称
     */
    private String activityName;
    /**
     * 优惠活动类型
     */
    private String discountType;
    /**
     * 优惠计算或使用规则
     */
    private String discountRule;
    /**
     * 单笔立减金额
     */
    private BigDecimal discountAmount;
    /**
     * 活动状态（0-停用，1-进行中）
     */
    private Integer status;
    /**
     * 活动累计优惠金额
     */
    private BigDecimal totalDiscountAmount;
    /**
     * 参与活动的订单数量
     */
    private Integer participateOrderCount;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
