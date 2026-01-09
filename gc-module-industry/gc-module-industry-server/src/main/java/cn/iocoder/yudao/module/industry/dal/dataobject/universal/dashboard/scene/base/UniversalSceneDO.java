package cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.base;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 通用场景表，一级和二级场景 DO
 *
 * @author lxs
 */
@TableName("biz_universal_scene")
@KeySequence("biz_universal_scene_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversalSceneDO extends BaseDO {

    /**
     * 主键，自增
     */
    @TableId
    private Long id;
    /**
     * 场景唯一标识
     */
    private String sceneCode;
    /**
     * 父级ID，0表示一级场景
     */
    private Long parentId;
    /**
     * 场景等级，一级场景为1，二级为2
     */
    private Integer level;
    /**
     * 场景描述
     */
    private String description;
    /**
     * 场景名称，如 城管住建/市政设施
     */
    private String label;
    /**
     * 场景值，用于前端选择，如 urban_admin / urban_admin_facility
     */
    private String value;
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
