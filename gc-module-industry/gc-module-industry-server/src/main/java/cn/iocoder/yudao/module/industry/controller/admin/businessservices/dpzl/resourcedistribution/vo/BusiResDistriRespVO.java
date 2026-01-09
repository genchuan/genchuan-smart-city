package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "资源分布查询响应 VO")
public class BusiResDistriRespVO {

    @Schema(description = "区域企业数列表")
    private List<RegionEntCount> regionEntList;

    @Schema(description = "行业企业分布列表")
    private List<IndustryEntCount> industryEntList;

    @Schema(description = "重点企业列表")
    private List<KeyEnt> keyEntList;

    @Schema(description = "企业规模分布列表")
    private List<ScaleEntCount> scaleEntList;

    /* ========== 内部静态类 ========== */
    @Data
    public static class RegionEntCount {
        @Schema(description = "区域名称")
        private String regionName;
        @Schema(description = "企业数量")
        private Integer entCount;
    }

    @Data
    public static class IndustryEntCount {
        @Schema(description = "行业名称")
        private String industryName;
        @Schema(description = "企业数量")
        private Integer count;
    }

    @Data
    public static class KeyEnt {
        @Schema(description = "企业名称")
        private String entName;
        @Schema(description = "企业规模")
        private String scale;
        @Schema(description = "企业位置")
        private String location;
    }

    @Data
    public static class ScaleEntCount {
        @Schema(description = "企业规模")
        private String scale;
        @Schema(description = "企业数量")
        private Integer count;
    }
}