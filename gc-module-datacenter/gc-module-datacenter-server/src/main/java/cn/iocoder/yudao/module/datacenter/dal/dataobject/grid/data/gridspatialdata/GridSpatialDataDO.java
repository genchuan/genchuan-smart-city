package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.gridspatialdata;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格空间数据 DO
 *
 * @author zhucongquan
 */
@TableName("biz_grid_spatial_data")
@KeySequence("biz_grid_spatial_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridSpatialDataDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 空间数据ID
     */
    private String spatialId;
    /**
     * 关联网格ID
     */
    private String gridId;
    /**
     * 网格类型
     */
    private String gridType;
    /**
     * 边界坐标
     */
    private String boundaryCoords;
    /**
     * 拓扑关系
     */
    private String topoRelation;
    /**
     * 坐标系
     */
    private String coordSystem;
    /**
     * 数据格式
     */
    private String dataFormat;
    /**
     * 备注
     */
    private String remark;
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