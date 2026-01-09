package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场景地图整体配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SceneMapConfigPageReqVO extends PageParam {

    @Schema(description = "所属场景唯一标识码（sceneKey）")
    private String sceneCode;

    @Schema(description = "默认图标宽度")
    private Integer defaultIconWidth;

    @Schema(description = "默认图标高度")
    private Integer defaultIconHeight;

    @Schema(description = "默认图标地址（未命中状态图标时使用）", example = "https://www.iocoder.cn")
    private String defaultIconUrl;

    @Schema(description = "是否显示图例：1显示，0不显示")
    private Integer showFlag;

    @Schema(description = "图例位置（如 bottomRight、bottomLeft）")
    private String position;

    @Schema(description = "地图中心纬度（WGS84）")
    private BigDecimal centerLat;

    @Schema(description = "地图中心经度（WGS84）")
    private BigDecimal centerLng;

    @Schema(description = "旋转速度（数值越小越慢）")
    private BigDecimal rotateSpeed;

    @Schema(description = "循环次数（infinity 表示无限循环，数字表示具体次数）", example = "2")
    private String loopType;

    @Schema(description = "俯仰角（控制 3D 效果）")
    private Integer pitch;

    @Schema(description = "地图缩放级别")
    private Integer zoom;

    @Schema(description = "中心点类型（复用地图中心坐标 / 自定义中心点）", example = "1")
    private String centerType;

    @Schema(description = "自定义中心点纬度（center_type 为自定义时生效）")
    private BigDecimal customCenterLat;

    @Schema(description = "自定义中心点经度（center_type 为自定义时生效）")
    private BigDecimal customCenterLng;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
