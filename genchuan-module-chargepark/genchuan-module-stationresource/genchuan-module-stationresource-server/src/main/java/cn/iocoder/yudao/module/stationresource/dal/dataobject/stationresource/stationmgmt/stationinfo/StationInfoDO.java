package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场站信息 DO
 *
 * @author 亘川智城
 */
@TableName("station_info")
@KeySequence("station_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationInfoDO extends BaseDO {

    /**
     * [主键ID] 主键，BIGINT，自增，必填
     */
    @TableId
    private Long id;
    /**
     * [场站编号] VARCHAR(32)，唯一，必填
     */
    private String stationNo;
    /**
     * [场站名称] VARCHAR(64)，必填
     */
    private String name;
    /**
     * [场站类型] 如：公共/商业/小区/产业，关联芋道字典表：station_info_type
     */
    private String type;
    /**
     * [场站地址] VARCHAR(255)，必填
     */
    private String address;
    /**
     * [泊位总数] INT，必填
     */
    private Integer spaceTotal;
    /**
     * [负责人] 关联芋道用户表system_user
     */
    private Long userId;
    /**
     * [收费标准] VARCHAR(255)
     */
    private String feeStandard;
    /**
     * [所属片区] 关联片区信息表area_info
     */
    private Long areaId;
    /**
     * [运营类型] 如：直接管理/甲方代运营/本地化部署/横向对接/数据互通，关联芋道字典表：station_info_operate_type
     */
    private String operateType;
    /**
     * [状态] 如：未生效/已生效/已禁用，关联芋道字典表：station_info_status
     */
    private String status;
    /**
     * [绑定时间] DATETIME
     */
    private LocalDateTime bindTime;
    /**
     * [绑定人] 关联芋道用户表system_user
     */
    private Long bindUserId;
    /**
     * [设备绑定数] INT，默认0
     */
    private Integer deviceCount;
    /**
     * [车位绑定数] INT，默认0
     */
    private Integer spaceCount;
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
