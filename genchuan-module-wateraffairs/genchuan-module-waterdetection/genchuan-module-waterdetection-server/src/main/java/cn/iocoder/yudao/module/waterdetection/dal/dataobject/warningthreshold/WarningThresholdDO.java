package cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningthreshold;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警阈值管理 DO
 *
 * @author zcq
 */
@TableName("gc_warning_threshold")
@KeySequence("gc_warning_threshold_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarningThresholdDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 指标名称
     */
    private String indicatorName;
    /**
     * 阈值类型(上限/下限)
     */
    private String thresholdType;
    /**
     * 阈值数值
     */
    private Double thresholdValue;
    /**
     * 单位
     */
    private String unit;
    /**
     * 适用场景(如管网末梢)
     */
    private String applicableScene;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;

}