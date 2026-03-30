package cn.iocoder.yudao.module.waterdetection.dal.dataobject.metercalibration;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测仪表校准管理 DO
 *
 * @author zcq
 */
@TableName("gc_meter_calibration")
@KeySequence("gc_meter_calibration_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeterCalibrationDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 仪表ID
     */
    private String meterId;
    /**
     * 仪表类型
     */
    private String meterType;
    /**
     * 校准周期(天)
     */
    private Double calibrationCycle;
    /**
     * 上次校准日期
     */
    private LocalDateTime lastCalibrationDate;
    /**
     * 本次校准日期
     */
    private LocalDateTime currentCalibrationDate;
    /**
     * 标准溶液浓度
     */
    private Double standardSolutionConc;
    /**
     * 校准前示值
     */
    private Double beforeCalibrationValue;
    /**
     * 校准后示值
     */
    private Double afterCalibrationValue;
    /**
     * 操作人员ID
     */
    private String operatorId;

}