package cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parksmoothparkingcard;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 畅停卡 DO
 *
 * @author lxs
 */
@TableName("park_smooth_parking_card")
@KeySequence("park_smooth_parking_card_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkSmoothParkingCardDO extends BaseDO {

    /**
     * [主键ID] 畅停卡唯一标识
     */
    @TableId
    private Long id;
    /**
     * [卡码] 唯一卡码
     */
    private String cardCode;
    /**
     * [卡种类型] 如:日卡/周卡/月卡/季卡/年卡/通用卡
     */
    private String cardType;
    /**
     * [卡名称] 畅停卡名称
     */
    private String cardName;
    /**
     * [适用范围类型] 如:全局/区域/车场
     */
    private String applyScopeType;
    /**
     * [适用范围值] 车场ID或12位行政区全码，英文逗号分隔
     */
    private String applyScopeValue;
    /**
     * [有效天数] 卡片有效天数
     */
    private Integer validDays;
    /**
     * [原价] 畅停卡原价
     */
    private BigDecimal originalPrice;
    /**
     * [售价] 畅停卡实际销售价格
     */
    private BigDecimal salePrice;
    /**
     * [状态] 如:未激活/已激活/已过期/已注销
     */
    private String status;
    /**
     * [持卡人ID] 持卡人用户ID
     */
    private Long userId;
    /**
     * [激活时间] 卡片激活时间
     */
    private LocalDateTime activateTime;
    /**
     * [过期时间] 卡片到期失效时间
     */
    private LocalDateTime expireTime;
    /**
     * [备注] 畅停卡相关备注说明
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
