package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreservation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车预约服务 DO
 *
 * @author lxs
 */
@TableName("biz_park_reservation")
@KeySequence("biz_park_reservation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkReservationDO extends BaseDO {

    /**
     * 主键，唯一标识
     */
    @TableId
    private Long id;
    /**
     * 预约记录唯一标识
     */
    private Long reservationId;
    /**
     * 预约订单编号
     */
    private String reservationNo;
    /**
     * 用户唯一标识
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String userPhone;
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
     * 预约开始时间
     */
    private LocalDateTime reserveStartTime;
    /**
     * 预约结束时间
     */
    private LocalDateTime reserveEndTime;
    /**
     * 预约状态
     */
    private String reserveStatus;
    /**
     * 核验方式
     */
    private String verifyMethod;
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 网格名称
     */
    private String gridName;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
