package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 异常订单 DO
 *
 * @author 亘川智城
 */
@TableName("abnormal_order")
@KeySequence("abnormal_order_seq")
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
     * 异常编号（唯一）
     */
    private String abnormalCode;

    /**
     * 订单编号，关联订单列表表order_list
     */
    private String orderCode;

    /**
     * 异常类型：支付异常/充电中断/设备故障
     */
    private String abnormalType;

    /**
     * 异常原因
     */
    private String abnormalReason;

    /**
     * 异常发生时间
     */
    private LocalDateTime abnormalTime;

    /**
     * 排查人员
     */
    private String checkUser;

    /**
     * 处理人员
     */
    private String handleUser;

    /**
     * 排查时间
     */
    private LocalDateTime checkTime;

    /**
     * 核实人
     */
    private String verifyUser;

    /**
     * 核实时间
     */
    private LocalDateTime verifyTime;

    /**
     * 核实结果：正常/异常/误报
     */
    private String verifyResult;

    /**
     * 核实备注
     */

    private String verifyRemark;

    /**
     * 处理措施
     */
    private String handleMeasure;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 异常状态：未核实/已核实/处理中/已完结
     */
    private String abnormalStatus;

    /**
     * 处理时间
     */
    private LocalDateTime handleTime;

    /**
     * 完结时间
     */
    private LocalDateTime completeTime;

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

    /**
     * 租户ID
     */
    private Long tenantId;

}