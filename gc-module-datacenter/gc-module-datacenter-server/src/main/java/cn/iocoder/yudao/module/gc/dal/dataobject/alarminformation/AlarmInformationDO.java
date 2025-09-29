package cn.iocoder.yudao.module.gc.dal.dataobject.alarminformation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警信息 DO
 *
 * @author 亘川智城
 */
@TableName("gc_alarm_information")
@KeySequence("gc_alarm_information_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmInformationDO extends BaseDO {

    /**
     * 预警ID
     */
    @TableId(type = IdType.INPUT)
    private String alarmId;
    /**
     * 预警编号
     */
    private String alarmCode;
    /**
     * 风险类型ID
     */
    private String riskTypeId;
    /**
     * 风险类型名称
     */
    private String riskTypeName;
    /**
     * 预警等级
     */
    private String alarmLevel;
    /**
     * 所属分域ID
     */
    private String domainId;
    /**
     * 所属分域名称
     */
    private String domainName;
    /**
     * 发生区域
     */
    private String occurRegion;
    /**
     * GPS坐标
     */
    private String gpsCoordinate;
    /**
     * 触发时间
     */
    private LocalDateTime triggerTime;
    /**
     * 预警状态
     */
    private String alarmStatus;
    /**
     * 触发原因
     */
    private String triggerReason;
    /**
     * 关联指标ID
     */
    private String indicatorId;
    /**
     * 处置责任人ID
     */
    private String handlerId;
    /**
     * 处置责任人姓名
     */
    private String handlerName;
    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;


}