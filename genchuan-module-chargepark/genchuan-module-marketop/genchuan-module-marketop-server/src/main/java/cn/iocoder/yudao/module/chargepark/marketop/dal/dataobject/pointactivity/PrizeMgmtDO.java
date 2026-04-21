package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("prize_mgmt")
@KeySequence("prize_mgmt_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrizeMgmtDO extends BaseDO {

    @TableId
    private Long id;
    /**
     * 奖品名称
     */
    private String name;
    /**
     * 奖品类型(实物/虚拟/优惠券/卡种)
     */
    private String type;
    /**
     * 当前库存
     */
    private Integer stock;
    /**
     * 状态(正常状态/禁用状态)
     */
    private String status;
    /**
     * 绑定活动ID
     */
    private Long activityId;
    /**
     * 发放量
     */
    private Integer sendCount;
    /**
     * 同步时间
     */
    private LocalDateTime syncTime;
    /**
     * 预警阈值
     */
    private Integer warnThreshold;
    /**
     * 奖品描述
     */
    private String description;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;

}
