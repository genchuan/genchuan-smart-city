// ResourceDistrRespVO.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 文旅资源分布视图 Response VO")
@Data
public class CultureResDistriRespVO {

    @Schema(description = "资源类型分布数据")
    private List<ResourceTypeDistribution> typeDistributions;

    @Schema(description = "资源密度热力图数据")
    private List<ResourceDensity> densityData;

    @Schema(description = "资源状态占比数据")
    private ResourceStatusRatio statusRatio;

    @Schema(description = "重点资源TOP5")
    private List<TopResource> topResources;

    @Schema(description = "资源类型列表(用于筛选下拉框)")
    private List<ResourceType> resourceTypes;

    @Data
    public static class ResourceTypeDistribution {
        @Schema(description = "资源类型名称")
        private String catName;
        @Schema(description = "场景总数")
        private Integer totalSceneCount;
    }

    @Data
    public static class ResourceDensity {
        @Schema(description = "X坐标")
        private Double coordX;
        @Schema(description = "Y坐标")
        private Double coordY;
        @Schema(description = "资源数量")
        private Integer count;
    }

    @Data
    public static class ResourceStatusRatio {
        @Schema(description = "正常资源数量")
        private Integer normalCompCount;
        @Schema(description = "停用资源数量")
        private Integer disabledSceneCount;
    }

    @Data
    public static class TopResource {
        @Schema(description = "企业名称")
        private String compName;
        @Schema(description = "热度值")
        private Integer count;
    }

    @Data
    public static class ResourceType {
        @Schema(description = "类型编码")
        private String minorCode;
        @Schema(description = "类型名称")
        private String minorName;
    }
}