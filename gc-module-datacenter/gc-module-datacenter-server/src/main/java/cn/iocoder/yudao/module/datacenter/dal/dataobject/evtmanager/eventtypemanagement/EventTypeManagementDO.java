package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.eventtypemanagement;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件类型管理 DO
 *
 * @author Gyh
 */
@TableName("gc_event_type_management")
@KeySequence("gc_event_type_management_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeManagementDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 事件类型ID，唯一编码，采用UUID
     */
    private String eventTypeId;

    /**
     * 事件大类代码，01-99
     */
    private String eventBigCode;

    /**
     * 事件大类名称，公共设施类/交通管理类/环境管理类等
     */
    private String eventBigName;

    /**
     * 事件中类代码，0101-9999
     */
    private String eventMidCode;

    /**
     * 事件中类名称，燃气设施/供水设施/道路设施等
     */
    private String eventMidName;

    /**
     * 事件小类代码，010101-999999
     */
    private String eventSmallCode;

    /**
     * 事件小类名称，燃气泄漏/供水管道破裂/道路坑洼等
     */
    private String eventSmallName;

    /**
     * 事件类型描述
     */
    private String eventTypeDesc;

    /**
     * 启用状态，启用/禁用
     */
    private String enableStatus;

    /**
     * 创建人账号
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人账号
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 扩展分类字段1，预留用于自定义标签
     */
    private String extendCategory1;

    /**
     * 扩展分类字段2，预留用于自定义标签
     */
    private String extendCategory2;

    /**
     * 扩展分类字段3，预留用于自定义标签
     */
    private String extendCategory3;

}