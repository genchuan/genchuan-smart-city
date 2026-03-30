package cn.iocoder.yudao.module.smartcity.dal.dataobject.eventreporting;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件上报 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_event_reporting")
@KeySequence("smartcity_event_reporting_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventReportingDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 事件编号
     */
    private String eventNumber;
    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件描述
     */
    private String eventDescription;
    /**
     * 发生时间
     */
    private LocalDateTime occurrenceTime;
    /**
     * 发生地点
     */
    private String place;
    /**
     * 经纬度坐标
     */
    private String latitudeLongitude;
    /**
     * 上报人
     */
    private String reportperson;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 上报方式
     */
    private String reportingMethod;
    /**
     * 关联部件编号
     */
    private String associatedComponentId;
    /**
     * 严重程度
     */
    private String severity;
    /**
     * 影响范围
     */
    private String reach;
    /**
     * 事件状态
     */
    private String eventStatus;

}