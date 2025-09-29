package cn.iocoder.yudao.module.datacenter.dal.dataobject.patrolroute;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查路线 DO
 *
 * @author zcq
 */
@TableName("gc_patrol_route")
@KeySequence("gc_patrol_route_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolRouteDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 路线名称
     */
    private String routeName;
    /**
     * 路线编码
     */
    private String routeCode;
    /**
     * 所属区域ID
     */
    private String areaId;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 路线类型
     */
    private String routeType;
    /**
     * 关联点位IDs
     */
    private String relatedPointIds;
    /**
     * 点位名称列表
     */
    private String pointNameList;
    /**
     * 路线长度
     */
    private Double routeLength;
    /**
     * 预计耗时(分钟)
     */
    private Integer estimatedTime;
    /**
     * 路线描述
     */
    private String routeDescription;
    /**
     * 启用状态(0-禁用,1-启用)
     */
    private Boolean enabledStatus;

}