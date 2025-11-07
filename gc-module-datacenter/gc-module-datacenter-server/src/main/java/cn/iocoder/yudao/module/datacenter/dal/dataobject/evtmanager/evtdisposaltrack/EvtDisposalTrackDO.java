package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtdisposaltrack;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件处置跟踪 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_disposal_track")
@KeySequence("biz_evt_disposal_track_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtDisposalTrackDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 跟踪ID，唯一编码，UUID
     */
    private String trackId;
    /**
     * 工单ID，关联事件工单表
     */
    private String woId;
    /**
     * 处置状态
     */
    private String handleStatus;
    /**
     * 当前处置节点
     */
    private String currentNode;
    /**
     * 处置人员
     */
    private String handlerName;
    /**
     * 处置开始时间，格式yyyy - MM - dd HH:mm:ss
     */
    private LocalDateTime startTime;
    /**
     * 预计完成时间，格式yyyy - MM - dd HH:mm:ss
     */
    private LocalDateTime estCompleteTime;

}