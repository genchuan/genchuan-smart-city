package cn.iocoder.yudao.module.smartcity.dal.dataobject.eventlist;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件列表 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_event_list")
@KeySequence("smartcity_event_list_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventListDO extends BaseDO {

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
     * 事件发生时间
     */
    private LocalDateTime eventOccurrenceTime;
    /**
     * 事件发生地点
     */
    private String locationOfTheIncident;
    /**
     * 事件描述
     */
    private String eventDescription;
    /**
     * 发现人
     */
    private String discoverer;
    /**
     * 备注
     */
    private String notes;

}