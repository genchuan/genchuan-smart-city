package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.eventtype;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 监测事件类别 DO
 *
 * @author zhucongquan
 */
@TableName("monitor_event_type")
@KeySequence("monitor_event_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上级事件类别ID
     */
    private Long parentTypeId;
    /**
     * 唯一事件编码
     */
    private String eventCode;
    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 关联监测部件类别ID
     */
    private Long relatedMonitorTypeId;
    /**
     * 默认告警等级：提示/一般/严重/紧急
     */
    private String alarmLevel;
    /**
     * 事件处理规则
     */
    private String handleRule;
    /**
     * 状态：启用/停用
     */
    private String eventStatus;
    /**
     * 业务备注
     */
    private String eventRemark;

}
