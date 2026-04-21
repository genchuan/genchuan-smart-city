package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("market_op_report")
@KeySequence("market_op_report_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketOpReportDO extends BaseDO {

    @TableId
    private Long id;
    /** 报表名称 */
    private String name;
    /** 报表类型（日报/周报/月报/季报/半年报/年报/自定义） */
    private String type;
    /** 时间粒度（day/week/month/quarter/half_year/year） */
    private String timeScale;
    /** 统计开始时间 */
    private LocalDateTime startTime;
    /** 统计结束时间 */
    private LocalDateTime endTime;
    /** 报表状态 */
    private String status;
    /** 备注 */
    private String remark;
    /** 活动参与率 */
    private Double activityJoinRate;
    /** 优惠券核销率 */
    private Double couponVerifyRate;
    /** 卡种销量 */
    private Integer cardSaleCount;

}
