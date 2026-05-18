package cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.datacollect;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 能耗采集 DO
 *
 * @author 亘川智城
 */
@TableName("energy_collect")
@KeySequence("energy_collect_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyCollectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备类型：电表/水表/气表
     */
    private String deviceType;
    /**
     * 能耗类型：电/水/气/热
     */
    private String energyType;
    /**
     * 采集时间
     */
    private LocalDateTime collectTime;
    /**
     * 采集状态：采集正常/采集异常
     */
    private String collectStatus;
    /**
     * 能耗数值
     */
    private BigDecimal energyValue;
    /**
     * 采集频率，单位分钟
     */
    private Integer collectFreq;
    /**
     * 异常次数
     */
    private Integer exceptionCount;
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