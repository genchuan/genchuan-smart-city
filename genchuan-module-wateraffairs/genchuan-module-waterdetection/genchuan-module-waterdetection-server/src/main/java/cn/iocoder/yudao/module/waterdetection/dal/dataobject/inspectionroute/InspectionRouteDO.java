package cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectionroute;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡检路线规划与优化 DO
 *
 * @author zcq
 */
@TableName("gc_inspection_route")
@KeySequence("gc_inspection_route_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionRouteDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 路线ID
     */
    private String routeId;
    /**
     * 巡检点ID
     */
    private String inspectionPointId;
    /**
     * 巡检点类型(水源地/水厂/管网节点)
     */
    private String pointType;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 预计到达时间
     */
    private LocalDateTime estimatedArrivalTime;
    /**
     * 实际到达时间
     */
    private LocalDateTime actualArrivalTime;
    /**
     * 路线调整原因
     */
    private String routeAdjustReason;

}