package cn.iocoder.yudao.module.datacenter.dal.dataobject.eventdisposition;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测事件关联处置 DO
 *
 * @author zcq
 */
@TableName("biz_event_disposition")
@KeySequence("biz_event_disposition_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDispositionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 事件类型ID
     */
    private String eventTypeId;
    /**
     * 事件类型名称
     */
    private String eventTypeName;
    /**
     * 行政区划编码
     */
    private String divisionCode;
    /**
     * 行政区划名称
     */
    private String divisionName;
    /**
     * 流程模型ID
     */
    private String processModelId;
    /**
     * 流程模型名称
     */
    private String processModelName;
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