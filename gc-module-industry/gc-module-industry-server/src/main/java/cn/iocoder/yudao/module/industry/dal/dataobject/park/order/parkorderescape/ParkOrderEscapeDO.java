package cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 逃费订单 DO
 *
 * @author lxs
 */
@TableName("park_order_escape")
@KeySequence("park_order_escape_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkOrderEscapeDO extends BaseDO {

    /**
     * 主键ID，自增，记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * 该订单编号
     */
    private String orderNo;
    /**
     * 逃费订单ID / 原订单ID，关联 park_order_temp.order_id 或 park_order_period.order_id
     */
    private Long escapeOrderId;
    /**
     * 车辆车牌号
     */
    private String carNumber;
    /**
     * 用户ID，关联 sys_user.id，可为空
     */
    private Long userId;
    /**
     * 车场ID，关联 park_lot.lot_id
     */
    private Long lotId;
    /**
     * 发生逃费的时间
     */
    private LocalDateTime escapeTime;
    /**
     * 逃费金额
     */
    private BigDecimal escapeAmount;
    /**
     * 逃费原因，包括：未缴费离场/设备故障/其他
     */
    private String escapeReason;
    /**
     * 追缴状态，包括：未追缴/追缴中/已追缴/无法追缴
     */
    private String traceStatus;
    /**
     * 追缴次数
     */
    private Integer traceTimes;
    /**
     * 上次追缴时间
     */
    private LocalDateTime lastTraceTime;
    /**
     * 追缴方式，包括：短信提醒/电话通知/系统限制/法律途径
     */
    private String traceWay;
    /**
     * 备注信息
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
