package cn.iocoder.yudao.module.kitchen.dal.dataobject.sysdevice;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备信息 DO
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
     * [主键ID] 设备唯一标识
     */
    @TableId
    private Long id;
    /**
     * [设备编号] 区域编码+设备类型+序号，唯一
     */
    private String deviceCode;
    /**
     * [设备名称] 如：后厨摄像头、AI识别设备
     */
    private String deviceName;
    /**
     * [设备类型] 如：摄像头、AI识别仪
     */
    private String deviceType;
    /**
     * [所属企业ID] 关联enterprise_info.id
     */
    private String entId;
    /**
     * [所属区域ID] 关联sys_area.id
     */
    private Long areaId;
    /**
     * [状态] 如：在线/离线/故障/停用/维修中
     */
    private String status;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;


}
