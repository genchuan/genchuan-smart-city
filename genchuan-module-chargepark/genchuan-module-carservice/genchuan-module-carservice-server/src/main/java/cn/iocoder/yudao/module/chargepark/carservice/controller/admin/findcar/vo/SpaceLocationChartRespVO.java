package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 车位定位统计图表 Response VO")
@Data
public class SpaceLocationChartRespVO {

    /** 占位字段:依赖车位经纬度,待外部车位模块上线后填充 */
    @Schema(description = "车位位置展示数据(地图渲染)。占位字段,待车位模块上线后填充")
    private List<Map<String, Object>> spaceLocationList;

    @Schema(description = "总查询量")
    private Integer totalQueryCount;

    @Schema(description = "定位成功率")
    private BigDecimal locationSuccessRate;

}
