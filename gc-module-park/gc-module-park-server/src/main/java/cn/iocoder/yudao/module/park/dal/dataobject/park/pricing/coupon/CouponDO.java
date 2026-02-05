package cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.coupon;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 优惠券 DO
 *
 * @author 亘川智城
 */
@TableName("park_coupon")
@KeySequence("park_coupon_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [优惠券码]
     */
    private String couponCode;
    /**
     * [持有者ID] 关联 park_user.id
     */
    private Long holderId;
    /**
     * [优惠券名称]
     */
    private String couponName;
    /**
     * [优惠券类型] 如:满减券/折扣券/免费时长券
     */
    private String couponType;
    /**
     * [面值/折扣比例]
     */
    private String faceValue;
    /**
     * [最低消费金额]
     */
    private BigDecimal minConsume;
    /**
     * [有效天数]
     */
    private Integer validDays;
    /**
     * [生效时间]
     */
    private LocalDateTime startTime;
    /**
     * [失效时间]
     */
    private LocalDateTime endTime;
    /**
     * [适用范围] 如:全局/区域/车场
     */
    private String applyScope;
    /**
     * [适用范围ID列表] JSON格式varchar
     */
    private String scopeIds;
    /**
     * [状态] 如:启用/禁用/已使用/已过期
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
