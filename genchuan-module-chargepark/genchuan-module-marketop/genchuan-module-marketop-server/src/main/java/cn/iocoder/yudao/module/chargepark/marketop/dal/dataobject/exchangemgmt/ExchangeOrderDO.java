package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("exchange_order")
@KeySequence("exchange_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeOrderDO extends BaseDO {

    @TableId
    private Long id;

    /** 订单编号(唯一) */
    private String no;

    /** 用户ID(关联system_user) */
    private Long userId;

    /** 类目ID(关联exchange_category) */
    private Long categoryId;

    /** 商品ID */
    private Long goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 消耗积分 */
    private Integer costPoint;

    /** 支付状态(待支付/已支付/已完成/已取消) */
    private String payStatus;

    /** 支付时间 */
    private LocalDateTime payTime;

    /** 发货时间 */
    private LocalDateTime shipTime;

    /** 物流信息 */
    private String logisticsInfo;

    /** 归档时间 */
    private LocalDateTime archiveTime;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;

}
