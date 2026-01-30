package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.deviceextend;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备扩展 DO
 *
 * @author zhucongquan
 */
@TableName("tb_device_extend")
@KeySequence("tb_device_extend_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceExtendDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 设备类型：道闸/摄像头/计费桩/传感器/边缘网关
     */
    private String deviceType;
    /**
     * 所属资产ID
     */
    private Long assetId;
    /**
     * 唯一设备编码
     */
    private String deviceCode;
    /**
     * 运行状态：在线/离线/故障/维护
     */
    private String deviceStatus;
    /**
     * 安装时间
     */
    private LocalDateTime installTime;
    /**
     * 上次维护时间
     */
    private LocalDateTime lastMaintainTime;
    /**
     * 下次维护时间
     */
    private LocalDateTime nextMaintainTime;
    /**
     * 业务创建时间
     */
    private LocalDateTime deviceCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime deviceUpdateTime;
    /**
     * 业务备注
     */
    private String deviceRemark;

}