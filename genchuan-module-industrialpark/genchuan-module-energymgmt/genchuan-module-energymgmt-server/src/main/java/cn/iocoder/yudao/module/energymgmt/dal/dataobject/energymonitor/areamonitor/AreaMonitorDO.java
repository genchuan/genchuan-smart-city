package cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.areamonitor;

import lombok.*;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 分区能耗 DO
 *
 * @author 亘川智城
 */
@TableName("area_monitor")
@KeySequence("area_monitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaMonitorDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 区域面积，单位㎡
     */
    private Integer areaSize;
    /**
     * 能耗总量
     */
    private BigDecimal totalEnergy;
    /**
     * 单位面积能耗
     */
    private BigDecimal unitEnergy;
    /**
     * 能耗状态：正常能耗/能耗异常
     */
    private String energyStatus;
    /**
     * 关联设备数
     */
    private Integer deviceCount;
    /**
     * 同比变化
     */
    private BigDecimal yoyChange;
    /**
     * 环比变化
     */
    private BigDecimal momChange;
    /**
     * 操作人账号
     */
    private String handleUser;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}