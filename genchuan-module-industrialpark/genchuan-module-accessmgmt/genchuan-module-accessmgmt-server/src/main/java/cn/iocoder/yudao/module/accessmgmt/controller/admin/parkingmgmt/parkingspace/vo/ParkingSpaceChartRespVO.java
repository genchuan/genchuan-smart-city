package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 车位管理态势 Response VO")
@Data
public class ParkingSpaceChartRespVO {

    @Schema(description = "停车场经纬度分布列表")
    private List<ParkMapItem> parkMapList;

    @Schema(description = "车位经纬度分布列表")
    private List<SpaceMapItem> spaceMapList;

    @Schema(description = "总车位数")
    private Integer totalSpace;

    @Schema(description = "空闲车位数")
    private Integer freeSpace;

    @Schema(description = "占用车位数")
    private Integer occupySpace;

    @Schema(description = "预约车位数")
    private Integer reserveSpace;

    @Schema(description = "各时段使用率列表")
    private List<UseRateItem> useRateList;

    @Schema(description = "各类型占比列表")
    private List<TypeRateItem> typeRateList;

    @Schema(description = "停车场地图分布项")
    @Data
    public static class ParkMapItem {
        @Schema(description = "经度")
        private String lon;
        @Schema(description = "纬度")
        private String lat;
        @Schema(description = "停车场名称")
        private String parkName;
        @Schema(description = "车位数")
        private Integer spaceCount;
    }

    @Schema(description = "车位地图分布项")
    @Data
    public static class SpaceMapItem {
        @Schema(description = "经度")
        private String lon;
        @Schema(description = "纬度")
        private String lat;
        @Schema(description = "车位编号")
        private String spaceCode;
    }

    @Schema(description = "使用率统计项")
    @Data
    public static class UseRateItem {
        @Schema(description = "名称")
        private String name;
        @Schema(description = "数值")
        private Integer value;
    }

    @Schema(description = "类型占比统计项")
    @Data
    public static class TypeRateItem {
        @Schema(description = "名称")
        private String name;
        @Schema(description = "数值")
        private Integer value;
    }

}
