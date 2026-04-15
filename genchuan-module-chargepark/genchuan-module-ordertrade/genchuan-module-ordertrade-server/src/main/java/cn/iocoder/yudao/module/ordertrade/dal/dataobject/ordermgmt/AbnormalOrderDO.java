package cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 异常订单 DO
 * @author genchuan
 */
@TableName("abnormal_order")
@KeySequence("abnormal_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AbnormalOrderDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 关联订单ID */
    private Long orderId;

    /** 订单类型，字典：abnormal_order_order_type */
    private String orderType;

    /** 异常类型，字典：abnormal_order_abnormal_type */
    private String abnormalType;

    /** 异常识别时间 */
    private LocalDateTime identifyTime;

    /** 处置状态，字典：abnormal_order_status */
    private String status;

    /** 所属场站ID */
    private Long stationId;

    /** 忽略理由 */
    private String ignoreReason;

    /** 处置进度 */
    private String processProgress;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
