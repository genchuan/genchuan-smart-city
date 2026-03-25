package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 窨井盖监测数据列表 VO
 * 用于前端列表展示，包含所有关联查询字段
 */
@Schema(description = "管理后台 - 窨井盖监测数据列表 VO")
@Data
public class ManholeMonitorVO {

    // 窨井盖基础信息
    private Long coverId;
    private String coverNo;
    private Long roadId;
    private String roadName;
    // 开合状态
    private Long openStatusId;
    private String openStatus;
    // 监测数据
    private BigDecimal tiltAngle;
    private BigDecimal vibrationData;
    // 风险等级
    private Long riskLevelId;
    private String riskLevel;
    // 设备信息
    private Long deviceId;
    private String deviceCode;
    private String deviceOnlineStatus;
    // 运维人员
    private String staffName;
    // 配置信息
    private Integer collectFrequency;
    private BigDecimal tiltAngleThreshold;
    // 监测状态 & 时间
    private String monitorStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}