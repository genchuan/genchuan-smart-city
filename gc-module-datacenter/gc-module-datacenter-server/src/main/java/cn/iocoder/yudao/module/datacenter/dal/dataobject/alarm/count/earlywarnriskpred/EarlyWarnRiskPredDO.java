package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnriskpred;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警风险趋势预测 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_risk_pred")
@KeySequence("stat_early_warn_risk_pred_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnRiskPredDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 预测ID
     */
    private String trendForecastId;
    /**
     * 预测对象
     */
    private String forecastObject;
    /**
     * 预测对象值
     */
    private String forecastObjectValue;
    /**
     * 历史数据周期
     */
    private String historyDataCycle;
    /**
     * 历史数据范围
     */
    private String historyDataRange;
    /**
     * 预测周期
     */
    private String forecastCycle;
    /**
     * 预测时间范围
     */
    private String forecastTimeRange;
    /**
     * 预测指标
     */
    private String forecastIndicators;
    /**
     * 预测模型
     */
    private String forecastModel;
    /**
     * 预测准确率
     */
    private BigDecimal forecastAccuracy;
    /**
     * 预测人ID
     */
    private String forecastUserId;
    /**
     * 预测人姓名
     */
    private String forecastUserName;
    /**
     * 预测时间
     */
    private LocalDateTime forecastTime;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}