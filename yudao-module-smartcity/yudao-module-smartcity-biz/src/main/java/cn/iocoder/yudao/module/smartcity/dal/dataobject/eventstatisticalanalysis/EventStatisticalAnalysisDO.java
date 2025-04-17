package cn.iocoder.yudao.module.smartcity.dal.dataobject.eventstatisticalanalysis;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件统计分析 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_event_statistical_analysis")
@KeySequence("smartcity_event_statistical_analysis_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventStatisticalAnalysisDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计周期
     */
    private String statisticalCycle;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件数量
     */
    private String numberOfEvents;
    /**
     * 发生地点
     */
    private String place;
    /**
     * 高发时段
     */
    private String highIncidencePeriod;
    /**
     * 平均处理时长
     */
    private String aht;
    /**
     * 处理成功率
     */
    private String processingSuccessRate;
    /**
     * 严重程度分布
     */
    private String severityDistribution;
    /**
     * 涉及部门
     */
    private String involvedDepartments;
    /**
     * 责任主体
     */
    private String responsibleParty;
    /**
     * 处理方式占比
     */
    private String proportionOfProcessing;
    /**
     * 资源消耗统计
     */
    private String resourceConsumption;
    /**
     * 不同渠道上报占比
     */
    private String proportionOfReporting;
    /**
     * 重复事件数量
     */
    private String numberOfRepeatedEvents;
    /**
     * 时间序列趋势
     */
    private String timeSeriesTrend;
    /**
     * 关联部件故障次数
     */
    private String numberOfRelated;
    /**
     * 公众反馈满意度评分
     */
    private String publicFeedbackSatisfaction;

}