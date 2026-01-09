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
    @Schema(description = "字段渲染配置列表", example = "[{\"key\":\"comp_name\",\"label\":\"企业名称\",\"renderType\":\"normal\",\"statusMap\":{\"0\":\"未处理\",\"1\":\"已处理\"}}]")
    private List<SelectedFieldConfigVO> selectedFieldsConfig;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_width / default_icon_height)
    @Schema(description = "默认图标尺寸", example = "{\"width\":24,\"height\":24}")
    private DefaultIconSizeVO defaultIconSize;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_url)
    @Schema(description = "默认图标URL", example = "https://example.com/default_icon.png")
    private String defaultIconUrl;

    // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code, raw_value, icon_url)
    @Schema(description = "状态字段图标配置列表", example = "[{\"fieldKey\":\"rectify_status\",\"rawValue\":\"1\",\"icon\":\"https://example.com/status_icon.png\"}]")
    private List<StatusIconConfigVO> statusIconConfigList;

    // show / position 来源表：biz_scene_map_config 场景级别的地图整体配置表 (show_flag / position)
    // items 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code, raw_value, legend_text, icon_url)
    @Schema(description = "图例配置", example = "{\"show\":true,\"position\":\"top-right\"}")
    private LegendConfigVO legendConfig;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_lat / center_lng)
    @Schema(description = "地图中心", example = "{\"lat\":26.855237,\"lng\":118.001000}")
    private MapCenterVO mapCenter;

    // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (rotate_speed, loop_type, pitch, zoom, center_type, custom_center_lat / custom_center_lng)
    @Schema(description = "环绕动画配置", example = "{\"rotateSpeed\":1.5,\"loop\":\"loop\",\"pitch\":45,\"zoom\":12,\"centerType\":\"custom\",\"customCenter\":{\"lat\":26.855237,\"lng\":118.001000}}")
    private OrbitAnimationVO orbitAnimation;

    @Data
    @Schema(description = "字段渲染配置")
    public static class SelectedFieldConfigVO {

        // 来源表：biz_scene_field 场景字段表 (field_code)
        @Schema(description = "字段标识", example = "comp_name")
        private String key;

        // 来源表：biz_scene_field 场景字段表 (label)
        @Schema(description = "字段标签", example = "企业名称")
        private String label;

        // 来源表：biz_scene_field 场景字段表 (type) (normal / coord / status)
        @Schema(description = "渲染类型", example = "normal")
        private String renderType;

        // 来源表：biz_selected_field_status_map 场景字段状态映射表 (raw_value -> display_text, 仅 status 类型字段)
        @Schema(description = "状态映射", example = "{\"0\":\"未处理\",\"1\":\"已处理\"}")
        private Map<String, String> statusMap;
    }

    @Data
    @Schema(description = "默认图标尺寸")
    public static class DefaultIconSizeVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_width)
        @Schema(description = "宽度", example = "24")
        private Integer width;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (default_icon_height)
        @Schema(description = "高度", example = "24")
        private Integer height;
    }

    @Data
    @Schema(description = "状态字段图标配置")
    public static class StatusIconConfigVO {

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code)
        @Schema(description = "字段标识", example = "rectify_status")
        private String fieldKey;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (raw_value)
        @Schema(description = "原始值", example = "1")
        private String rawValue;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (icon_url)
        @Schema(description = "图标URL", example = "https://example.com/status_icon.png")
        private String icon;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 的图例展示文本（legend 中显示的文字，可为空）(raw_value)
        @Schema(description = "原始值", example = "1")
        private String legend_text;
    }

    @Data
    @Schema(description = "图例配置")
    public static class LegendConfigVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (show_flag)
        @Schema(description = "是否显示", example = "true")
        private Boolean show;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (position)
        @Schema(description = "位置", example = "top-right")
        private String position;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code, raw_value, legend_text, icon_url)
        @Schema(description = "图例项列表")
        private List<LegendItemVO> items;
    }

    @Data
    @Schema(description = "图例项")
    public static class LegendItemVO {

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (field_code)
        @Schema(description = "字段标识", example = "rectify_status")
        private String fieldKey;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (raw_value)
        @Schema(description = "原始值", example = "1")
        private String rawValue;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (legend_text)
        @Schema(description = "显示文本", example = "已处理")
        private String text;

        // 来源表：biz_scene_status_icon 场景状态字段图标配置表 (icon_url)
        @Schema(description = "图标URL", example = "https://example.com/legend_icon.png")
        private String icon;
    }

    @Data
    @Schema(description = "地图中心坐标")
    public static class MapCenterVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_lat)
        @Schema(description = "纬度", example = "26.855237")
        private BigDecimal lat;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_lng)
        @Schema(description = "经度", example = "118.001000")
        private BigDecimal lng;
    }

    @Data
    @Schema(description = "环绕动画配置")
    public static class OrbitAnimationVO {

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (rotate_speed)
        @Schema(description = "旋转速度", example = "1.5")
        private BigDecimal rotateSpeed;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (loop_type)
        @Schema(description = "循环类型", example = "loop")
        private String loop;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (pitch)
        @Schema(description = "俯仰角", example = "45")
        private Integer pitch;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (zoom)
        @Schema(description = "缩放", example = "12")
        private Integer zoom;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (center_type)
        @Schema(description = "中心类型", example = "custom")
        private String centerType;

        // 来源表：biz_scene_map_config 场景级别的地图整体配置表 (custom_center_lat / custom_center_lng)
        @Schema(description = "自定义中心坐标", example = "{\"lat\":26.855237,\"lng\":118.001000}")
        private MapCenterVO customCenter;
    }
}
