package cn.iocoder.yudao.module.integratedsecurity.dal.dataobject.videomonitor.realtimemonitor;

import lombok.*;
import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 实时监控 DO
 *
 * @author 亘川智城
 */
@TableName("real_time_monitor")
@KeySequence("real_time_monitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealTimeMonitorDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 摄像头ID 关联camera_mgmt表id
     */
    private Long cameraId;
    /**
     * 摄像头名称
     */
    private String cameraName;
    /**
     * 安装区域
     */
    private String area;
    /**
     * 运行状态：正常/异常 关联字典：real_time_monitor_run_status
     */
    private String runStatus;
    /**
     * 告警状态：无告警/告警中 关联字典：real_time_monitor_alarm_status
     */
    private String alarmStatus;
    /**
     * 监控画面地址
     */
    private String imgUrl;
    /**
     * 经度
     */
    private BigDecimal lon;
    /**
     * 纬度
     */
    private BigDecimal lat;
    /**
     * 告警内容
     */
    private String alarmContent;
    /**
     * 操作人账号 关联芋道用户表
     */
    private String handleUser;
    /**
     * 处置结果
     */
    private String handleResult;
    /**
     * 截图记录
     */
    private String snapImg;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}