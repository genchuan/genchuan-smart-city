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
 * 错时停车订单 DO
 * @author genchuan
 */
@TableName("offtime_park_order")
@KeySequence("offtime_park_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OfftimeParkOrderDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 订单编号，唯一 */
    private String orderNo;

    /** 车牌 */
    private String plateNo;

    /** 预约开始时间 */
    private LocalDateTime reserveStartTime;

    /** 预约结束时间 */
    private LocalDateTime reserveEndTime;

    /** 实际使用开始时间 */
    private LocalDateTime actualStartTime;

    /** 实际使用结束时间 */
    private LocalDateTime actualEndTime;

    /** 订单金额 */
    private BigDecimal amount;

    /** 支付状态，字典：offtime_park_order_status */
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

    /** 支付时间 */
    private LocalDateTime payTime;

    /** 支付方式，字典：offtime_park_order_pay_method */
    private String payMethod;

    /** 归档时间 */
    private LocalDateTime archiveTime;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
