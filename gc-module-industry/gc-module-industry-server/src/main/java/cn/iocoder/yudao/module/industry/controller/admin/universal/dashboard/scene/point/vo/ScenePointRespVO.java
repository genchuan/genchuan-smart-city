package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 场景点位数据 Response VO")
@Data
public class ScenePointRespVO {

    // 纬度，如 26.85523
    @Schema(description = "纬度", example = "26.85523")
    @ExcelProperty("纬度")
    private Double lat;

    // 经度，如 118.0010
    @Schema(description = "经度", example = "118.0010")
    @ExcelProperty("经度")
    private Double lng;

    // 点位名称
    @Schema(description = "点位名称", example = "某某设备点位")
    @ExcelProperty("点位名称")
    private String pointName;

    //专属字段列表
    @Schema(description = "专属字段列表")
    @ExcelProperty("专属字段列表")
    private List<ExclusiveField> exclusiveFieldList;

}
