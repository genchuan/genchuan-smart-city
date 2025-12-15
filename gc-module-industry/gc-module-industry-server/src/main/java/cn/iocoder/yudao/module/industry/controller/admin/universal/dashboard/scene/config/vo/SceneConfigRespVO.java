package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 获取场景配置接口 Response VO")
@Data
public class SceneConfigRespVO {

    // 来源表：biz_scene_field 场景字段表 (field_code)
    @Schema(description = "已选择的字段标识列表", example = "[\"comp_name\",\"scenic_level\"]")
    private List<String> selectedFields;

    // 来源表：biz_scene_field 场景字段表 (field_code, label, type)
    // statusMap 来源表：biz_selected_field_status_map 场景字段状态映射表 (raw_value -> display_text, 仅 status 类型字段)
    @Schema(description = "字段渲染配置列表")
    private List<SelectedFieldConfigVO> selectedFieldsConfig;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_width / default_icon_height)
    @Schema(description = "默认图标尺寸")
    private DefaultIconSizeVO defaultIconSize;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_url)
    @Schema(description = "默认图标URL")
    private String defaultIconUrl;

    // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code, raw_value, icon_url)
    @Schema(description = "状态字段图标配置列表")
    private List<StatusIconConfigVO> statusIconConfigList;

    // show / position 来源表：biz_scene_map_config 场景级别的地图整体配置表 (show_flag / position)
    // items 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code, raw_value, legend_text, icon_url)
    @Schema(description = "图例配置")
    private LegendConfigVO legendConfig;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_lat / center_lng)
    @Schema(description = "地图中心")
    private MapCenterVO mapCenter;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (rotate_speed, loop_type, pitch, zoom, center_type, custom_center_lat / custom_center_lng)
    @Schema(description = "环绕动画配置")
    private OrbitAnimationVO orbitAnimation;

    @Data
    @Schema(description = "字段渲染配置")
    public static class SelectedFieldConfigVO {

        // 来源表：biz_scene_field 场景字段表 (field_code)
        private String key;

        // 来源表：biz_scene_field 场景字段表 (label)
        private String label;

        // 来源表：biz_scene_field 场景字段表 (type) (normal / coord / status)
        private String renderType;

        // 来源表：biz_selected_field_status_map 场景字段状态映射表 (raw_value -> display_text, 仅 status 类型字段)
        private Map<String, String> statusMap;
    }

    @Data
    @Schema(description = "默认图标尺寸")
    public static class DefaultIconSizeVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_width)
        private Integer width;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_height)
        private Integer height;
    }

    @Data
    @Schema(description = "状态字段图标配置")
    public static class StatusIconConfigVO {

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code)
        private String fieldKey;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (raw_value)
        private String rawValue;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (icon_url)
        private String icon;
    }

    @Data
    @Schema(description = "图例配置")
    public static class LegendConfigVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (show_flag)
        private Boolean show;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (position)
        private String position;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code, raw_value, legend_text, icon_url)
        private List<LegendItemVO> items;
    }

    @Data
    @Schema(description = "图例项")
    public static class LegendItemVO {

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code)
        private String fieldKey;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (raw_value)
        private String rawValue;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (legend_text)
        private String text;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (icon_url)
        private String icon;
    }

    @Data
    @Schema(description = "地图中心坐标")
    public static class MapCenterVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_lat)
        private BigDecimal lat;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_lng)
        private BigDecimal lng;
    }

    @Data
    @Schema(description = "环绕动画配置")
    public static class OrbitAnimationVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (rotate_speed)
        private BigDecimal rotateSpeed;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (loop_type)
        private String loop;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (pitch)
        private Integer pitch;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (zoom)
        private Integer zoom;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_type)
        private String centerType;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (custom_center_lat / custom_center_lng)
        private MapCenterVO customCenter;
    }
}
