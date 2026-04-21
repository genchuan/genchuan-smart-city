package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("point_lottery")
@KeySequence("point_lottery_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointLotteryDO extends BaseDO {

    @TableId
    private Long id;
    /**
     * 抽奖记录编号
     */
    private String no;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 奖品ID
     */
    private Long prizeId;
    /**
     * 抽奖时间
     */
    private LocalDateTime lotteryTime;
    /**
     * 消耗积分
     */
    private Integer costPoint;
    /**
     * 记录状态(正常记录/异常记录/已核查)
     */
    private String status;
    /**
     * 发放时间
     */
    private LocalDateTime sendTime;
    /**
     * 发放人
     */
    private Long senderId;
    /**
     * 核查结果
     */
    private String checkResult;
    /**
     * 同步状态(未同步/已同步/同步失败)
     */
    private String syncStatus;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;

}
