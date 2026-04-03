package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder;

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
 * 异常订单 DO
 *
 * @author 亘川智城
 */
@TableName("abnormal_order")
@KeySequence("abnormal_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbnormalOrderDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 订单编号，关联订单列表表order_list
     */
    private String orderCode;
    /**
     * 异常类型：支付异常/充电中断/设备故障，关联字典abnormal_order_abnormal_type
     */
    private String abnormalType;
    /**
     * 异常原因
     */
    private String abnormalReason;
    /**
     * 排查人员
     */
    private String checkUser;
    /**
     * 排查时间
     */
    private LocalDateTime checkTime;
    /**
     * 处理措施
     */
    private String handleMeasure;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 异常状态：未核实/已核实/处理中/已完结，关联字典abnormal_order_abnormal_status
     */
    private String abnormalStatus;
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
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