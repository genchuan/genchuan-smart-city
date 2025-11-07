package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtarch;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件办结归档 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_arch")
@KeySequence("biz_evt_arch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtArchDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 归档ID，唯一编码，UUID
     */
    private String archId;
    /**
     * 工单ID，关联事件工单表
     */
    private String woId;
    /**
     * 事件编码，关联事件分级分拨表
     */
    private String evtCode;
    /**
     * 办结时间，格式yyyy - MM - dd HH:mm:ss
     */
    private LocalDateTime completeTime;
    /**
     * 办结说明
     */
    private String completeDesc;

}