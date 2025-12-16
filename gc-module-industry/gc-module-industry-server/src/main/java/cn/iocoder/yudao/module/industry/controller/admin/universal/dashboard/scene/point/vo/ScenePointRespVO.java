package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

//@Schema(description = "管理后台 - 场景点位数据 Response VO")
//@Data
//public class ScenePointRespVO {
//
//    // 纬度，如 26.85523
//    @Schema(description = "纬度", example = "26.85523")
//    @ExcelProperty("纬度")
//    private Double lat;
//
//    // 经度，如 118.0010
//    @Schema(description = "经度", example = "118.0010")
//    @ExcelProperty("经度")
//    private Double lng;
//
//    // 点位名称
//    @Schema(description = "点位名称", example = "某某设备点位")
//    @ExcelProperty("点位名称")
//    private String pointName;
//
//    //专属字段列表
//    @Schema(description = "专属字段列表")
//    @ExcelProperty("专属字段列表")
//    private List<ExclusiveField> exclusiveFieldList;
//
//}

@Schema(description = "管理后台 - 场景点位数据 Response VO")
@Data
public class ScenePointRespVO {

    @Schema(description = "点位唯一标识", example = "order_001")
    private String uniqueId;

    @Schema(description = "点位名称", example = "解放西路占道经营点")
    private String comp_name;

//    @Schema(description = "违规类型", example = "占道经营")
//    private String violation_type;
//
//    @Schema(description = "整改状态", example = "未整改")
//    private String rectify_status;

    @Schema(description = "纬度", example = "26.855237")
    private Double lat;

    @Schema(description = "经度", example = "118.001000")
    private Double lng;

        //专属字段列表
    @Schema(description = "专属字段列表")
    @ExcelProperty("专属字段列表")
    private List<ExclusiveField> exclusiveFieldList;
    @Schema(description = "坐标对象", example = "{\"lat\":26.855237,\"lng\":118.001000}")
    private Coord coord;

    @Data
    public static class Coord {
        private Double lat;
        private Double lng;

        public Coord(Double lat, Double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }
}

