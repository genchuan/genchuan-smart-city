package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkpay;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车缴费服务 DO
 *
 * @author lxs
 */
@TableName("biz_park_pay")
@KeySequence("biz_park_pay_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPayDO extends BaseDO {

    /**
     * 主键ID，唯一标识
     */
    @TableId
    private Long id;
    /**
     * 缴费记录唯一标识
     */
    private String payCode;
    /**
     * 关联订单编号
     */
    private String woNo;
    /**
     * 关联预约记录ID
     */
    private Long reservationId;
    /**
     * 支付订单编号
     */
    private String payWoNo;
    /**
     * 停车场唯一标识
     */
    private Long parkLotId;
    /**
     * 停车场名称
     */
    private String parkLotName;
    /**
     * 泊位唯一标识
     */
    private Long berthId;
    /**
     * 泊位编号
     */
    private String berthNo;
    /**
     * 车辆车牌号
     */
    private String plateNum;
    /**
     * 车辆入场时间
     */

    private LocalDateTime entryTime;
    /**
     * 车辆出场时间
     */
    private LocalDateTime exitTime;
    /**
     * 停车持续时间（分钟）
     */
    private Integer parkEndure;
    /**
     * 支付完成时间
     */
    private LocalDateTime payTime;
    /**
     * 应收金额
     */
    private BigDecimal receivableAmount;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 实际支付金额
     */
    private BigDecimal actualPayAmount;
    /**
     * 支付方式
     */
    private String payMethod;
    /**
     * 支付状态
     */
    private String payStatus;
    /**
     * 放行状态
     */
    private String releaseStatus;
    /**
     * 优惠活动名称
     */
    private String activityName;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 行政区划12位地址码
     */
    private String regionFullCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 网格名称
     */
    private String gridName;
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
