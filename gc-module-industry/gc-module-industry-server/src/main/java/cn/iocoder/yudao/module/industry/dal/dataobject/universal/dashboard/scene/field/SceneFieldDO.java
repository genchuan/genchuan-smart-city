package cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.field;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场景字段 DO
 *
 * @author lxs
 */
@TableName("biz_scene_field")
@KeySequence("biz_scene_field_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneFieldDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 场景标识
     */
    private String sceneCode;
    /**
     * 字段标识
     */
    private String fieldCode;
    /**
     * 字段名称
     */
    private String label;

    /**
     * 是否选择（1/0）
     */
    private Integer isSelected;
    /**
     * 字段类型：normal/status/coord
     */
    private String type;
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
