package cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtinfo;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测事件信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_mon_evt_info")
@KeySequence("biz_mon_evt_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonEvtInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 事件ID，唯一编码，UUID生成
     */
    private String monEvtId;
    /**
     * 事件标识码，按18位规则生成，如110101020300200001；监测事件标识码规则表 (sys_mon_evt_code_rule)
     */
    private String evtCode;
    /**
     * 事件名称，关联分类名称，如 “燃气泄漏事件”；监测事件分类配置表 (sys_mon_evt_cat)
     */
    private String evtName;
    /**
     * 事件分类ID，关联事件分类表小类ID，监测事件分类配置表 (sys_mon_evt_cat)
     */
    private String evtCatId;
    /**
     * 关联部件ID，关联监测部件信息表部件ID，监测部件信息表 (biz_mon_comp_info)
     */
    private String relCompId;
    /**
     * 关联部件名称，与部件ID同步，自动填充，监测部件信息表 (biz_mon_comp_info)
     */
    private String relCompName;
    /**
     * 事发位置，事件发生位置，如 “XX路与XX路交叉口”
     */
    private String incidentPos;
    /**
     * 事发坐标X，经度，2000国家大地坐标系
     */
    private BigDecimal incidentX;
    /**
     * 事发坐标Y，纬度，2000国家大地坐标系
     */
    private BigDecimal incidentY;
    /**
     * 事件等级，一级/二级/三级，事件严重程度，参照GB/T XXXXX.6
     */
    private String evtLevel;
    /**
     * 处置状态，待处置/处置中/已办结/已驳回，事件处置进度
     */
    private String handleStatus;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}