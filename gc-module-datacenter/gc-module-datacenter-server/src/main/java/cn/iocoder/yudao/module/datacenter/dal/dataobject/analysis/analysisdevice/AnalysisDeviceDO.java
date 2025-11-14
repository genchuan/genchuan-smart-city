package cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisdevice;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按设备分域分析研判统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_analysis_device")
@KeySequence("stat_analysis_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisDeviceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID，唯一编码，UUID生成
     */
    private String statAnalysisId;
    /**
     * 统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM
     */
    private String statCycle;
    /**
     * 统计周期名称，如“2025年09月”
     */
    private String statCycleName;
    /**
     * 设备大类ID，关联设备分类表
     */
    private String deviceMajorId;
    /**
     * 设备大类名称，与大类ID同步，关联设备分类表
     */
    private String deviceMajorName;
    /**
     * 设备小类ID，关联设备分类表
     */
    private String deviceMinorId;
    /**
     * 设备小类名称，与小类ID同步，关联设备分类表
     */
    private String deviceMinorName;
    /**
     * 设备总数量，周期内该分类设备总数量
     */
    private Integer totalDeviceCount;
    /**
     * 设备在线率，在线设备数/总数量×100，0.00-100.00
     */
    private BigDecimal deviceOnlineRate;
    /**
     * 数据上传频率，周期内该分类设备平均每小时上传数据次数，次/小时
     */
    private BigDecimal dataUploadFreq;
    /**
     * 设备故障率，故障设备数/总数量×100，0.00-100.00
     */
    private BigDecimal deviceFaultRate;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;
    /**
     * 分类扩展字段1，预留
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留
     */
    private String extCommon2;

}