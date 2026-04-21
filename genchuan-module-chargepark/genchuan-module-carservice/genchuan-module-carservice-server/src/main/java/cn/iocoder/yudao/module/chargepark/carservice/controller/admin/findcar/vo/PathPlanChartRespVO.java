package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 路径规划统计图表 Response VO")
@Data
public class PathPlanChartRespVO {

    /** 占位字段:依赖路径经纬度坐标点,待外部地图服务接入后填充 */
    @Schema(description = "路径展示数据(地图渲染),包含路径坐标点。占位字段,待地图服务接入后填充")
    private List<Map<String, Object>> pathList;

    @Schema(description = "总规划量")
    private Integer totalPlanCount;

    @Schema(description = "规划成功率")
    private BigDecimal planSuccessRate;

}
