package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridtopiclayer;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 网格专题图层管理 DO
 *
 * @author zcq
 */
@TableName("biz_grid_topic_layer")
@KeySequence("biz_grid_topic_layer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridTopicLayerDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 图层ID
     */
    private String layerId;
    /**
     * 图层名称
     */
    private String layerName;
    /**
     * 网格类型
     */
    private String gridType;
    /**
     * 比例尺
     */
    private String scale;
    /**
     * 边界线样式ID
     */
    private String boundaryStyleId;
    /**
     * 注记样式ID
     */
    private String annotateStyleId;
    /**
     * 显示状态
     */
    private String displayStatus;
    /**
     * 图层顺序
     */
    private Integer layerWo;
    /**
     * 创建人
     */
    private String createUserId;
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