package cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 路线 DO
 *
 * @author 芋道源码
 */
@TableName("sys_route")
@KeySequence("sys_route_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String sysRouteId;
    /**
     * 路线名称
     */
    private String name;
    /**
     * 路线编码
     */
    private String code;
    /**
     * 起点（含经度、纬度、具体地址）
     */
    private String startPoint;
    /**
     * 终点（含经度、纬度、具体地址）
     */
    private String endPoint;
    /**
     * 路线经纬度坐标（数组格式：[{"lng":116.40,"lat":39.91},{"lng":116.41,"lat":39.92}]）
     */
    private String routePoints;
    /**
     * 路线长度（单位：公里）
     */
    private BigDecimal length;
    /**
     * 状态（可选值：0-禁用/1-启用）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}