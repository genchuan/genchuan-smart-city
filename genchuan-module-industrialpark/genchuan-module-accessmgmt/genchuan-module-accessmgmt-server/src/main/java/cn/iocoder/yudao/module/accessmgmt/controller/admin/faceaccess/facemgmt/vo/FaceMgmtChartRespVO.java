package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 人脸通行授权态势 Response VO")
@Data
public class FaceMgmtChartRespVO {

    @Schema(description = "人脸总数")
    private Integer totalCount;

    @Schema(description = "授权数")
    private Integer authCount;

    @Schema(description = "过期数")
    private Integer expiredCount;

    @Schema(description = "未授权数")
    private Integer unAuthCount;

    @Schema(description = "各区域授权人数统计列表")
    private List<AreaAuthItem> areaAuthList;

    @Schema(description = "各时段通行人数统计列表")
    private List<TimeAccessItem> timeAccessList;

    @Schema(description = "区域授权统计项")
    @Data
    public static class AreaAuthItem {

        @Schema(description = "区域名称")
        private String area;

        @Schema(description = "授权人数")
        private Integer count;
    }

    @Schema(description = "时段通行统计项")
    @Data
    public static class TimeAccessItem {

        @Schema(description = "时段")
        private String time;

        @Schema(description = "通行人数")
        private Integer count;
    }

}
