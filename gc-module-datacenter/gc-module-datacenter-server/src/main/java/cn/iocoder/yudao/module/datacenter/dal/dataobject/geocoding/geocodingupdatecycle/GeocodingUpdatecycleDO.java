package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingupdatecycle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 数据更新周期配置 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_updatecycle")
@KeySequence("gc_geocoding_updatecycle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingUpdatecycleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 更新周期ID
     */
    private String updateCycleId;
    /**
     * 更新对象类型
     */
    private String updateObjectType;
    /**
     * 常规更新周期
     */
    private String regularCycle;
    /**
     * 特殊更新触发条件
     */
    private String specialTriggerCondition;
    /**
     * 更新责任部门
     */
    private String updateDept;
    /**
     * 更新流程说明
     */
    private String updateProcessDesc;
    /**
     * 启用状态(0-禁用,1-启用)
     */
    private Integer enableStatus;
    /**
     * 配置人
     */
    private String configUser;
    /**
     * 配置时间
     */
    private LocalDateTime configTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分类扩展字段1
     */
    private String extCategory1;
    /**
     * 分类扩展字段2
     */
    private String extCategory2;
    /**
     * 分类扩展字段3
     */
    private String extCategory3;

}