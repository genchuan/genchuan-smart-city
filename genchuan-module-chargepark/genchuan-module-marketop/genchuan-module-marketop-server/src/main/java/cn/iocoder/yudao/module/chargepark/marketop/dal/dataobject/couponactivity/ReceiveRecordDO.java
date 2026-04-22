package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("receive_record")
@KeySequence("receive_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveRecordDO extends BaseDO {

    @TableId
    private Long id;
    /** 记录编号 */
    private String no;
    /** 用户ID */
    private Long userId;
    /** 优惠券ID */
    private Long couponId;
    /** 领取时间 */
    private LocalDateTime receiveTime;
    /** 状态(正常记录=1/异常记录=-1/已核查=1) */
    private String status;
    /** 核销时间 */
    private LocalDateTime verifyTime;
    /** 核查结果 */
    private String checkResult;
    /** 同步状态(未同步/已同步/同步失败) */
    private String syncStatus;
    /** 归档时间 */
    private LocalDateTime archiveTime;
    private String reserve1;
    private String reserve2;

}
