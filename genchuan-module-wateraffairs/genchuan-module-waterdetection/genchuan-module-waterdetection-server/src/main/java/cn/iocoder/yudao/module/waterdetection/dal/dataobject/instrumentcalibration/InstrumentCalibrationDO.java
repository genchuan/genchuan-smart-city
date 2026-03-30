package cn.iocoder.yudao.module.waterdetection.dal.dataobject.instrumentcalibration;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 仪器零点/量程漂移校验 DO
 *
 * @author zcq
 */
@TableName("gc_instrument_calibration")
@KeySequence("gc_instrument_calibration_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentCalibrationDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 仪器ID
     */
    private String instrumentId;
    /**
     * 校验日期
     */
    private LocalDateTime calibrationDate;
    /**
     * 零点校正液浓度
     */
    private Double zeroPointConc;
    /**
     * 零点漂移值
     */
    private Double zeroDrift;
    /**
     * 量程校正液浓度
     */
    private Double spanConc;
    /**
     * 量程漂移值
     */
    private Double spanDrift;
    /**
     * 校验结果
     */
    private String calibrationResult;
    /**
     * 操作人员ID
     */
    private String operatorId;

}