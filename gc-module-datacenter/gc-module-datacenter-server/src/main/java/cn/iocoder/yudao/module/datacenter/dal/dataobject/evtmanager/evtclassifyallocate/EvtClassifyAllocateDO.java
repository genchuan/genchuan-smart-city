package cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtclassifyallocate;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 事件分级分拨 DO
 *
 * @author 亘川智城
 */
@TableName("biz_evt_classify_allocate")
@KeySequence("biz_evt_classify_allocate_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvtClassifyAllocateDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 分拨ID，唯一编码，UUID
     */
    private String allocateId;
    /**
     * 事件编码，关联事件研判分析表
     */
    private String evtCode;
    /**
     * 事件类型ID，关联事件类型配置表
     */
    private String evtTypeId;
    /**
     * 事件类型名称，与类型ID同步
     */
    private String evtTypeName;
    /**
     * 紧急程度，一般/紧急/特急，从研判表同步
     */
    private String urgencyLevel;
    /**
     * 分拨部门代码，关联部门信息表，分拨目标部门
     */
    private String allocateDeptCode;
    /**
     * 分拨部门名称，与部门代码同步
     */
    private String allocateDeptName;
    /**
     * 分拨人ID，分拨调度员账号，关联用户信息表(sys_user)
     */
    private String allocatorId;
    /**
     * 分拨人姓名，与分拨人ID同步
     */
    private String allocatorName;
    /**
     * 分拨时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成
     */
    private LocalDateTime allocateTime;
    /**
     * 分拨说明，分拨备注，如“优先处置，需1小时内反馈”
     */
    private String allocateDesc;

}