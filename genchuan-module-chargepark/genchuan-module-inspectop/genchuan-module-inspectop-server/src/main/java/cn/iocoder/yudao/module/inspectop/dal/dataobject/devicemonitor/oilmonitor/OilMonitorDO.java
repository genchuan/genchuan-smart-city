package cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.oilmonitor;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 油车占位监测 DO
 *
 * @author zhucongquan
 */
@TableName("oil_monitor")
@KeySequence("oil_monitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OilMonitorDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车位ID
     */
    private Long spaceId;
    /**
     * 场站ID
     */
    private Long stationId;
    /**
     * 识别时间
     */
    private LocalDateTime identifyTime;
    /**
     * 处置状态
     */
    private String processStatus;
    /**
     * 处置人ID
     */
    private Long processUserId;
    /**
     * 处置时间
     */
    private LocalDateTime processTime;
    /**
     * 忽略理由
     */
    private String ignoreReason;
    /**
     * 处置进度
     */
    private Integer processProgress;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}
