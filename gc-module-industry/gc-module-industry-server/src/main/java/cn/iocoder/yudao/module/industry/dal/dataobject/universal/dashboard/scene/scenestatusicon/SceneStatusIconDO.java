package cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenestatusicon;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场景状态字段图标配置 DO
 *
 * @author lxs
 */
@TableName("biz_scene_status_icon")
@KeySequence("biz_scene_status_icon_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneStatusIconDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 所属场景唯一标识码（sceneKey）
     */
    private String sceneCode;
    /**
     * 状态字段唯一标识码（对应 selectedFieldsConfig.key）
     */
    private String fieldCode;
    /**
     * 状态原始值（对应业务数据中的字段值）
     */
    private String rawValue;
    /**
     * 该状态对应的地图点位图标地址
     */
    private String iconUrl;
    /**
     * 图例展示文本（legend 中显示的文字，可为空）
     */
    private String legendText;
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
