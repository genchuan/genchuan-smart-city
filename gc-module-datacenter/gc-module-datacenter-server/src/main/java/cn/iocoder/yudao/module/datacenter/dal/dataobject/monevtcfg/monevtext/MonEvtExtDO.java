package cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtext;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 扩展监测事件配置 DO
 *
 * @author 亘川智城
 */
@TableName("sys_mon_evt_ext")
@KeySequence("sys_mon_evt_ext_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonEvtExtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 扩展ID，唯一编码，UUID生成
     */
    private String monEvtExtId;
    /**
     * 扩展小类代码，从080倒排
     */
    private String extMinorCode;
    /**
     * 扩展小类名称，如“智能充电桩过载事件”
     */
    private String extMinorName;
    /**
     * 所属中类ID，关联事件分类表中类ID；监测事件分类配置表(sys_mon_evt_cat)
     */
    private String parentMidId;
    /**
     * 所属中类名称，与中类ID同步，自动填充；监测事件分类配置表(sys_mon_evt_cat)
     */
    private String parentMidName;
    /**
     * 关联部件分类ID，关联部件分类表小类ID；监测部件分类配置表(sys_mon_comp_cat)
     */
    private String relCompCatId;
    /**
     * 关联部件分类名称，与部件分类ID同步，自动填充；监测部件分类配置表(sys_mon_comp_cat)
     */
    private String relCompCatName;
    /**
     * 扩展说明，描述扩展小类用途，如“用于记录充电桩过载事件”
     */
    private String extDesc;
    /**
     * 申请原因，说明扩展必要性，如“现有分类无法覆盖充电桩过载场景”
     */
    private String applyReason;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}