package cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdeviceinfo;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 救援设备信息 DO
 *
 * @author 亘川智城
 */
@TableName("biz_emer_device_info")
@KeySequence("biz_emer_device_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmerDeviceInfoDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 设备ID，唯一编码，UUID
     */
    private String deviceId;
    /**
     * 设备编码，系统自动生成
     */
    private String deviceCode;
    /**
     * 设备名称，标准名称
     */
    private String deviceName;
    /**
     * 分类ID，关联救援设备分类配置表
     */
    private String catId;
    /**
     * 分类名称，与分类ID同步
     */
    private String catName;
    /**
     * 适用区域代码，关联行政区划表
     */
    private String applyRegionCode;
    /**
     * 适用区域名称，与区域代码同步
     */
    private String applyRegionName;
    /**
     * 设备型号，如“XX型救援设备”
     */
    private String deviceModel;
    /**
     * 设备数量，该类型设备的总数量
     */
    private Integer deviceCount;
    /**
     * 可用数量，可投入救援的设备数量
     */
    private Integer availableDeviceCount;
    /**
     * 设备状态，1（正常）/0（异常），默认1
     */
    private String deviceStatus;
    /**
     * 创建人，创建人账号，关联用户信息表(sys_user)
     */
    private String createUser;
    /**
     * 更新人，更新人账号，关联用户信息表(sys_user)
     */
    private String updateUser;

}