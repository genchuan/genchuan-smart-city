package cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.selectedfieldstatusmap;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场景字段状态映射 DO
 *
 * @author lxs
 */
@TableName("biz_selected_field_status_map")
@KeySequence("biz_selected_field_status_map_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectedFieldStatusMapDO extends BaseDO {

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
     * 字段唯一标识码（对应 selectedFieldsConfig.key，仅 status 类型字段有效）
     */
    private String fieldCode;
    /**
     * 状态原始值（来自业务数据，如“正常开放”）
     */
    private String rawValue;
    /**
     * 状态展示文本（前端显示用）
     */
    private String displayText;
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
