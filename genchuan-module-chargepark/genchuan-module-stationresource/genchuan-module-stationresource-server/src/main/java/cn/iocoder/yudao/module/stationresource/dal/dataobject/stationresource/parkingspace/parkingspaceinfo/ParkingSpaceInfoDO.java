package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车位信息 DO
 *
 * @author 亘川智城
 */
@TableName("parking_space_info")
@KeySequence("parking_space_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpaceInfoDO extends BaseDO {

    /**
     * [主键ID] 主键，BIGINT，自增，必填
     */
    @TableId
    private Long id;
    /**
     * [车位编号] VARCHAR(32)，唯一，必填
     */
    private String spaceNo;
    /**
     * [所属场站] 关联场站信息表station_info，必填
     */
    private Long stationId;
    /**
     * [所属车库] VARCHAR(64)，必填
     */
    private String garage;
    /**
     * [车位位置] VARCHAR(64)
     */
    private String location;
    /**
     * [车位类型] 如：普通车位/充电车位，关联芋道字典表：parking_space_info_type
     */
    private String type;
    /**
     * [设备类型] 如：地锁/充电桩/摄像头，关联芋道字典表：parking_space_info_device_type
     */
    private String deviceType;
    /**
     * [车位二维码] VARCHAR(255)
     */
    private String qrcode;
    /**
     * [状态] 如：未绑定/已绑定/已禁用，关联芋道字典表：parking_space_info_status
     */
    private String status;
    /**
     * [实时状态] 如：空闲/占用/故障，关联芋道字典表：parking_space_info_real_status
     */
    private String realStatus;
    /**
     * [绑定时间] DATETIME
     */
    private LocalDateTime bindTime;
    /**
     * [绑定人] 关联芋道用户表system_user
     */
    private Long bindUserId;
    /**
     * [绑定设备] BIGINT
     */
    private Long deviceId;
    /**
     * [状态更新时间] DATETIME
     */
    private LocalDateTime statusUpdateTime;
    /**
     * [备注] TEXT
     */
    private String remark;
    /**
     * [备用字段1] VARCHAR(100)
     */
    private String reserve1;
    /**
     * [备用字段2] VARCHAR(100)
     */
    private String reserve2;


}
