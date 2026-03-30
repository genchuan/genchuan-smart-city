package cn.iocoder.yudao.module.waterdetection.dal.dataobject.onlinelabcomparison;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 在线数据与实验室比对 DO
 *
 * @author zcq
 */
@TableName("gc_online_lab_comparison")
@KeySequence("gc_online_lab_comparison_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnlineLabComparisonDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 比对日期
     */
    private LocalDateTime comparisonDate;
    /**
     * 监测点ID
     */
    private String monitorPointId;
    /**
     * 仪器类型
     */
    private String instrumentType;
    /**
     * 在线监测值
     */
    private Double onlineValue;
    /**
     * 实验室检测值
     */
    private Double labValue;
    /**
     * 偏差值
     */
    private Double deviationValue;
    /**
     * 是否超标(0否1是)
     */
    private Boolean isExceeded;
    /**
     * 预警状态
     */
    private String warningStatus;

}