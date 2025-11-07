package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 按行政区域统计 Response VO")
@Data
public class GeocodingStatByAdminRespVO {

    @Schema(description = "行政区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String adminCode;

    @Schema(description = "行政区域名称")
    private String adminName;

    @Schema(description = "区域数据数量")
    private Integer regionCount = 0;

    @Schema(description = "地片与区片数据数量")
    private Integer zoneCount = 0;

    @Schema(description = "街巷数据数量")
    private Integer streetCount = 0;

    @Schema(description = "门牌楼牌数据数量")
    private Integer houseCount = 0;

    @Schema(description = "兴趣点数据数量")
    private Integer poiCount = 0;

    @Schema(description = "新增数据总数")
    private Integer newDataTotal = 0;

    @Schema(description = "更新数据总数")
    private Integer updateDataTotal = 0;

    @Schema(description = "统计时间")
    private String statTime;

    @Schema(description = "统计描述")
    private String description;
}