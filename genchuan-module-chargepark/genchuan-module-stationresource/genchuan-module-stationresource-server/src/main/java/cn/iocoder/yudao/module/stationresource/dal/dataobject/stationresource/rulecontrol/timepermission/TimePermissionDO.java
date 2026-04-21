package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.timepermission;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 时段权限 DO
 *
 * @author 亘川智城
 */
@TableName("time_permission")
@KeySequence("time_permission_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimePermissionDO extends BaseDO {

    /**
     * [主键ID] 主键，时段权限记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [所属场站] 关联场站信息表station_info的ID
     */
    private Long stationId;
    /**
     * [生效时段] 时段权限生效的时间段描述
     */
    private String timeRange;
    /**
     * [准入权限] 如：内部车辆/外部车辆/无牌车/会员车辆
     */
    private String permission;
    /**
     * [状态] 如：待生效/已生效/已禁用
     */
    private String status;
    /**
     * [审核时间] 权限审核通过的时间
     */
    private LocalDateTime auditTime;
    /**
     * [审核人] 关联芋道用户表system_user的ID
     */
    private Long auditUserId;
    /**
     * [使用次数] 该权限被使用的累计次数
     */
    private Integer useCount;
    /**
     * [最长停留时长] 单位：分钟
     */
    private Integer maxStay;
    /**
     * [工作日配置] 工作日时段细分配置，varchar
     */
    private String workdayConfig;
    /**
     * [节假日配置] 节假日时段细分配置，varchar
     */
    private String holidayConfig;
    /**
     * [高峰配置] 高峰时段细分配置，varchar
     */
    private String peakConfig;
    /**
     * [平峰配置] 平峰时段细分配置，varchar
     */
    private String offpeakConfig;
    /**
     * [备注] 时段权限相关备注说明
     */
    private String remark;
    /**
     * [备用字段1] 预留扩展字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 预留扩展字段2
     */
    private String reserve2;


}
