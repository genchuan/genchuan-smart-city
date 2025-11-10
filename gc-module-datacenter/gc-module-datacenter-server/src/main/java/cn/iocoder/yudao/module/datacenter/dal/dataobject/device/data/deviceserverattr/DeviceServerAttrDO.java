package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceserverattr;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备服务器属性配置 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_server_attr")
@KeySequence("sys_device_server_attr_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceServerAttrDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 属性配置ID
     */
    private String serverAttrCfgId;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性代码
     */
    private String attrCode;
    /**
     * 属性值
     */
    private String attrValue;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 是否必选
     */
    private String isRequired;
    /**
     * 属性说明
     */
    private String attrDesc;
    /**
     * 配置时间
     */
    private LocalDateTime cfgTime;
    /**
     * 配置人
     */
    private String cfgUser;
    /**
     * 更新时间
     */
    private LocalDateTime updateTimeSys;
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