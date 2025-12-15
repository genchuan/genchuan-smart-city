package cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenemapconfig;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场景地图整体配置 DO
 *
 * @author lxs
 */
@TableName("biz_scene_map_config")
@KeySequence("biz_scene_map_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SceneMapConfigDO extends BaseDO {

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
     * 默认图标宽度
     */
    private Integer defaultIconWidth;
    /**
     * 默认图标高度
     */
    private Integer defaultIconHeight;
    /**
     * 默认图标地址（未命中状态图标时使用）
     */
    private String defaultIconUrl;
    /**
     * 是否显示图例：1显示，0不显示
     */
    private Integer showFlag;
    /**
     * 图例位置（如 bottomRight、bottomLeft）
     */
    private String position;
    /**
     * 地图中心纬度（WGS84）
     */
    private BigDecimal centerLat;
    /**
     * 地图中心经度（WGS84）
     */
    private BigDecimal centerLng;
    /**
     * 旋转速度（数值越小越慢）
     */
    private BigDecimal rotateSpeed;
    /**
     * 循环次数（infinity 表示无限循环，数字表示具体次数）
     */
    private String loopType;
    /**
     * 俯仰角（控制 3D 效果）
     */
    private Integer pitch;
    /**
     * 地图缩放级别
     */
    private Integer zoom;
    /**
     * 中心点类型（复用地图中心坐标 / 自定义中心点）
     */
    private String centerType;
    /**
     * 自定义中心点纬度（center_type 为自定义时生效）
     */
    private BigDecimal customCenterLat;
    /**
     * 自定义中心点经度（center_type 为自定义时生效）
     */
    private BigDecimal customCenterLng;
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
