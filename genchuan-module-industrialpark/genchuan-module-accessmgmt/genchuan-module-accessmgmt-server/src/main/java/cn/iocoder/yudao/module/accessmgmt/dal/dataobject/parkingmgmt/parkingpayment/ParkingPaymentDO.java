package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.parkingpayment;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 停车缴费 DO
 *
 * @author 亘川智城
 */
@TableName("parking_payment")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingPaymentDO extends BaseDO {

    /**
     * [主键ID] 主键
     */
    @TableId
    private Long id;
    /**
     * [账单编号] 账单编号
     */
    private String billCode;
    /**
     * [车牌号] 车牌号
     */
    private String plateNo;
    /**
     * [停车时长] 停车时长（分钟）
     */
    private Integer parkDuration;
    /**
     * [费用金额] 费用金额
     */
    private BigDecimal feeAmount;
    /**
     * [账单状态] 待缴费/已缴费/已欠费
     */
    private String billStatus;
    /**
     * [支付方式] 微信/支付宝/现金
     */
    private String payType;
    /**
     * [支付时间] 支付时间
     */
    private LocalDateTime payTime;
    /**
     * [发票状态] 已开具/未开具
     */
    private String invoiceStatus;
    /**
     * [优惠金额] 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * [优惠原因] 优惠原因
     */
    private String discountReason;
    /**
     * [操作人账号] 操作人账号
     */
    private String handleUser;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;

}
