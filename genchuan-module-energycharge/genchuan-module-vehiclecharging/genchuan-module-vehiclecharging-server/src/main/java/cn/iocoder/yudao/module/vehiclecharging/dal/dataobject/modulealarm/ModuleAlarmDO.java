package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.modulealarm;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 模块告警记录 DO
 *
 * @author 亘川智城
 */
@TableName("module_alarm_record")
@KeySequence("module_alarm_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleAlarmDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 告警编号
     */
    private String alarmCode;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 异常类型ID，关联 sys_dict_data 主键（接口故障/运行卡顿/上报异常）
     */
    private Long abnormalTypeId;
    /**
     * 告警等级ID，关联 sys_dict_data 主键（一般/严重）
     */
    private Long alarmLevelId;
    /**
     * 告警时间
     */
    private LocalDateTime alarmTime;
    /**
     * 服务器信息
     */
    private String serverInfo;
    /**
     * 告警状态ID，关联 sys_dict_data 主键（未排查/已排查/修复中/已销账）
     */
    private Long alarmStatusId;
    /**
     * 排查原因
     */
    private String checkReason;
    /**
     * 修复凭证
     */
    private String repairVoucher;
    /**
     * 修复时间
     */
    private LocalDateTime repairTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
