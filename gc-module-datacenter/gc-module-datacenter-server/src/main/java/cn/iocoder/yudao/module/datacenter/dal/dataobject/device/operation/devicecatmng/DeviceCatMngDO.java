package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicecatmng;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备分类管理 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_cat_mng")
@KeySequence("sys_device_cat_mng_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCatMngDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类管理ID
     */
    private String catMngId;
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
     * 设备大类ID
     */
    private String deviceMajorId;
    /**
     * 设备大类名称
     */
    private String deviceMajorName;
    /**
     * 设备中类ID
     */
    private String deviceMidId;
    /**
     * 设备中类名称
     */
    private String deviceMidName;
    /**
     * 设备小类ID
     */
    private String deviceMinorId;
    /**
     * 设备小类名称
     */
    private String deviceMinorName;
    /**
     * 分类时间
     */
    private LocalDateTime catTime;
    /**
     * 分类人
     */
    private String catUser;
    /**
     * 分类状态
     */
    private String catStatus;
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