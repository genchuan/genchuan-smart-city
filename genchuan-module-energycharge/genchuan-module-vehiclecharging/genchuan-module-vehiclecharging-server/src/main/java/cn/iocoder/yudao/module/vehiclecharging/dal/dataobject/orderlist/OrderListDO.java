package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderlist;

import cn.iocoder.yudao.framework.excel.core.annotations.ExcelColumnSelect;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单列表 DO
 *
 * @author 亘川智城
 */
@TableName("order_list")
@KeySequence("order_list_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 车牌号
     */
    private String plateNo;
    /**
     * 充电桩编号
     */
    private String pileCode;
    /**
     * 充电时长
     */
    private Integer chargeTime;
    /**
     * 充电量
     */
    private BigDecimal chargeAmount;
    /**
     * 充电金额
     */
    private BigDecimal chargeMoney;
    /**
     * 支付状态：未支付/已支付
     */
    private String payStatus;
    /**
     * 订单状态：待支付/已支付/充电中/已完成/已取消
     */
    private String orderStatus;
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 终止充电原因
     */
    private String stopReason;
    /**
     * 评价
     */
    private String evaluate;
    /**
     * 评价时间
     */
    private LocalDateTime evaluateTime;
    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;
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
     * 取消订单原因
     */
    @TableField(exist = false)
    private String cancelReason;

}