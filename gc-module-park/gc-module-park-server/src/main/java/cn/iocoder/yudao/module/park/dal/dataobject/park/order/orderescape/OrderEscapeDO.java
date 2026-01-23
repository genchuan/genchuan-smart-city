package cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderescape;

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
 * 逃费订单 DO
 *
 * @author 亘川智城
 */
@TableName("park_order_escape")
@KeySequence("park_order_escape_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEscapeDO extends BaseDO {

    /**
     * [主键ID] 逃费订单唯一标识
     */
    @TableId
    private Long id;
    /**
     * [原临停订单ID] 关联原临停订单ID，park_order_temp.id
     */
    private Long originalOrderId;
    /**
     * [车牌号码] 停车车辆车牌号码
     */
    private String carNumber;
    /**
     * [所属车场ID] 所属车场ID，关联 park_lot.id
     */
    private Long lotId;
    /**
     * [逃费金额] 逃费金额
     */
    private BigDecimal escapeAmount;
    /**
     * [逃费时间] 逃费发生时间
     */
    private LocalDateTime escapeTime;
    /**
     * [逃费类型] 如:未缴费离场/设备故障逃费/其他
     */
    private String escapeType;
    /**
     * [逃费等级] 如:轻度/中度/重度
     */
    private String escapeLevel;
    /**
     * [黑名单状态] 如:未列入/已列入
     */
    private String blacklistStatus;
    /**
     * [追缴状态] 如:未追缴/追缴中/已追缴/无法追缴
     */
    private String traceStatus;
    /**
     * [追缴次数] 追缴次数
     */
    private Integer traceCount;
    /**
     * [上次追缴时间] 上次追缴时间
     */
    private LocalDateTime lastTraceTime;
    /**
     * [备注] 逃费订单相关备注说明
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
