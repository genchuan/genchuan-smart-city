package cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 设备 DO
 *
 * @author 亘川智城
 */
@TableName("sys_device")
@KeySequence("sys_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDeviceDO extends BaseDO {

    /**
     * [主键ID] 主键，设备唯一标识
     */
    @TableId
    private Long id;
    /**
     * [设备编码] UUID格式
     */
    private String deviceCode;
    /**
     * [设备名称] 设备名称
     */
    private String name;
    /**
     * [设备在线状态] 如:在线/离线/异常
     */
    private String onlineStatus;
    /**
     * [设备分类] 如道路设施监测等等
     */
    private String category;
    /**
     * [设备描述] 设备描述
     */
    private String description;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}
