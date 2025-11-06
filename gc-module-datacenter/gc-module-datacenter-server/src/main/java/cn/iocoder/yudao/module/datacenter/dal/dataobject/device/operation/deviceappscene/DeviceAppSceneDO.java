package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceappscene;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备关联应用场景 DO
 *
 * @author zhucongquan
 */
@TableName("rel_device_app_scene")
@KeySequence("rel_device_app_scene_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAppSceneDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String deviceRelAppSceneId;
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
     * 应用场景ID
     */
    private String appSceneId;
    /**
     * 应用场景名称
     */
    private String appSceneName;
    /**
     * 应用场景编码
     */
    private String appSceneCode;
    /**
     * 关联时间
     */
    private LocalDateTime relTime;
    /**
     * 关联人
     */
    private String relUser;
    /**
     * 关联状态
     */
    private String relStatus;
    /**
     * 调整时间
     */
    private LocalDateTime adjustTime;
    /**
     * 调整人
     */
    private String adjustUser;
    /**
     * 调整原因
     */
    private String adjustReason;
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