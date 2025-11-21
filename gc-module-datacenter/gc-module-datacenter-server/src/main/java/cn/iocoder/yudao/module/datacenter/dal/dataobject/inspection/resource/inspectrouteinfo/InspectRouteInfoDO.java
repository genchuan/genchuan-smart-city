package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectrouteinfo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检路线信息 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_route_info")
@KeySequence("biz_inspect_route_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectRouteInfoDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 路线ID
     */
    private String routeId;
    /**
     * 路线名称
     */
    private String routeName;
    /**
     * 路线编码
     */
    private String routeCode;
    /**
     * 所属行政区划代码
     */
    private String areaCode;
    /**
     * 所属行政区划名称
     */
    private String areaName;
    /**
     * 起点名称
     */
    private String startPtName;
    /**
     * 起点坐标X
     */
    private BigDecimal startPtX;
    /**
     * 起点坐标Y
     */
    private BigDecimal startPtY;
    /**
     * 终点名称
     */
    private String endPtName;
    /**
     * 终点坐标X
     */
    private BigDecimal endPtX;
    /**
     * 终点坐标Y
     */
    private BigDecimal endPtY;
    /**
     * 途经网格IDs
     */
    private String passGridIds;
    /**
     * 途经网格名称
     */
    private String passGridNames;
    /**
     * 预计时长
     */
    private Integer estimateEndure;
    /**
     * 巡查频次
     */
    private String inspectFrequency;
    /**
     * 负责人员ID
     */
    private String personId;
    /**
     * 负责人员姓名
     */
    private String personName;
    /**
     * 路线状态
     */
    private Boolean routeStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间（业务）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间（业务）
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}