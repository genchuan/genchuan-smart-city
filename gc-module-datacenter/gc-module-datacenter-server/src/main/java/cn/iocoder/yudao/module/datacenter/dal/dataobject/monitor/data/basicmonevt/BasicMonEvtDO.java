package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonevt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 基础监测事件 DO
 *
 * @author zcq
 */
@TableName("stat_basic_mon_evt")
@KeySequence("stat_basic_mon_evt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicMonEvtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 事件ID
     */
    private String evtId;
    /**
     * 事件编码
     */
    private String evtCode;
    /**
     * 事件类型
     */
    private String evtType;
    /**
     * 关联点位ID
     */
    private String relPtId;
    /**
     * 关联点位名称
     */
    private String relPtName;
    /**
     * 关联指标ID
     */
    private String relIdxId;
    /**
     * 关联指标名称
     */
    private String relIdxName;
    /**
     * 关联设备编码
     */
    private String relDeviceCode;
    /**
     * 关联设备名称
     */
    private String relDeviceName;
    /**
     * 事件数值
     */
    private String evtValue;
    /**
     * 事发时间
     */
    private LocalDateTime evtTime;
    /**
     * 处置状态
     */
    private String dealStatus;
    /**
     * 派单部门代码
     */
    private String deptCode;
    /**
     * 派单部门名称
     */
    private String deptName;
    /**
     * 处置人ID
     */
    private Long dealUserId;
    /**
     * 处置人姓名
     */
    private String dealUserName;
    /**
     * 处置结果
     */
    private String dealResult;
    /**
     * 处置时间
     */
    private LocalDateTime dealTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTimeSys;
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