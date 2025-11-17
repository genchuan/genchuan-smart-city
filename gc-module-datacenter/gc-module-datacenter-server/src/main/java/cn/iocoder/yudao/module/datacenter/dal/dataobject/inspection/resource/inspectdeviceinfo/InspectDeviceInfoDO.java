package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectdeviceinfo;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检设备信息 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_device_info")
@KeySequence("biz_inspect_device_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectDeviceInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 所属网格ID
     */
    private String gridId;
    /**
     * 所属网格名称
     */
    private String gridName;
    /**
     * 关联人员ID
     */
    private String personId;
    /**
     * 关联人员姓名
     */
    private String personName;
    /**
     * 设备状态
     */
    private String deviceStatus;
    /**
     * 安装时间
     */
    private LocalDate installTime;
    /**
     * 保修截止时间
     */
    private LocalDate warrantyEndTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime bizUpdateTime;
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