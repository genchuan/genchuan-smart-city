package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("cycle_report")
@KeySequence("cycle_report_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CycleReportDO extends BaseDO {

    @TableId
    private Long id;
    /** 报表周期（日报/周报/月报/季报/半年报/年报/自定义报表） */
    private String reportCycle;
    /** 统计开始时间 */
    private LocalDateTime statStartTime;
    /** 统计结束时间 */
    private LocalDateTime statEndTime;
    /** 活动数 */
    private Integer activityCount;
    /** 参与用户数 */
    private Integer joinUserCount;
    /** 抽奖量 */
    private Integer lotteryCount;
    /** 中奖率 */
    private BigDecimal winningRate;
    /** 优惠券发放量 */
    private Integer couponSendCount;
    /** 核销率 */
    private BigDecimal couponVerifyRate;
    /** 卡种订单量 */
    private Integer cardOrderCount;
    /** 营收 */
    private BigDecimal revenue;
    /** 兑换量 */
    private Integer exchangeCount;
    /** 总库存 */
    private Integer totalStock;
    /** 预警库存数 */
    private Integer warnStockCount;
    /** 报表生成状态（已生成/生成中/生成失败） */
    private String generateStatus;
    /** 报表生成时间 */
    private LocalDateTime generateTime;
    /** 操作人 */
    private String operator;
    /** 生成耗时(ms) */
    private Integer generateCost;
    /** 报表导出次数 */
    private Integer exportCount;
    /** 筛选规则 */
    private String filterRule;
    /** 租户ID */
    private Long tenantId;

}
