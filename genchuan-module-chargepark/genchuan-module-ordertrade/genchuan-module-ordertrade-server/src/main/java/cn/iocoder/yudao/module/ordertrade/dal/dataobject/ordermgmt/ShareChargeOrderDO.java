package cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 共享充电订单 DO
 * @author genchuan
 */
@TableName("share_charge_order")
@KeySequence("share_charge_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShareChargeOrderDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 订单编号，唯一 */
    private String orderNo;

    /** 用户ID */
    private Long userId;

    /** 用户昵称 */
    private String userNickname;

    /** 借出时间 */
    private LocalDateTime lendTime;

    /** 归还时间 */
    private LocalDateTime returnTime;

    /** 实际使用时长（分钟） */
    private Integer actualDuration;

    /** 订单金额 */
    private BigDecimal amount;

    /** 订单状态，字典：share_charge_order_status */
    private String status;

    /** 订单生成时间 */
    private LocalDateTime createOrderTime;

    /** 所属场站ID */
    private Long stationId;

    /** 所属场站名称（关联 station_info.name，非数据库字段） */
    @TableField(exist = false)
    private String stationName;

    /** 开票状态（关联 invoice_list.status，非数据库字段） */
    @TableField(exist = false)
    private String invoiceStatus;

    /** 支付方式，字典：share_charge_order_pay_method */
    private String payMethod;

    /** 优惠抵扣金额 */
    private BigDecimal discountAmount;

    /** 归档时间 */
    private LocalDateTime archiveTime;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
