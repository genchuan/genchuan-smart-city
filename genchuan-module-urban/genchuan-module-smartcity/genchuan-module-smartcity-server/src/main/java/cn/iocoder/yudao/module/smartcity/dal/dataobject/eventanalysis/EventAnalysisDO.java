package cn.iocoder.yudao.module.smartcity.dal.dataobject.eventanalysis;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件关联分析 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_event_analysis")
@KeySequence("smartcity_event_analysis_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventAnalysisDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 关联分析编号
     */
    private String analysisNumber;
    /**
     * 主事件编号
     */
    private String mainEventNumber;
    /**
     * 主事件名称
     */
    private String mainEventName;
    /**
     * 关联事件编号
     */
    private String relatedEventNumber;
    /**
     * 关联事件名称
     */
    private String relatedEventName;
    /**
     * 关联类型
     */
    private String associationType;
    /**
     * 关联强度
     */
    private String associationStrength;
    /**
     * 分析时间
     */
    private LocalDateTime analysisTime;
    /**
     * 分析人员
     */
    private String analysts;
    /**
     * 分析结论
     */
    private String conclusion;
    /**
     * 关联证据
     */
    private String relatedEvidence;
    /**
     * 建议措施
     */
    private String recommendedMeasure;

}