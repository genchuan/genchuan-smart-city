package cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningindicator;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警指标配置 DO
 *
 * @author zcq
 */
@TableName("gc_warning_indicator")
@KeySequence("gc_warning_indicator_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarningIndicatorDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 预警指标名称
     */
    private String indicatorName;
    /**
     * 指标类型(水质/设备)
     */
    private String indicatorType;
    /**
     * 关联监测点类型(水源/水厂/管网)
     */
    private String relatedPointType;
    /**
     * 数据来源(在线监测/人工检测)
     */
    private String dataSource;

}