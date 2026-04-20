package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 充停地图统计图表 Response VO")
@Data
public class ChargeParkMapChartRespVO {

    /**
     * 场站与车位分布数据(地图渲染)。
     *
     * 占位字段:当前返回空 List,因为 carservice 模块不存场站/车位实体表,
     * 数据来源应为 vehiclecharging 模块的场站表 + park 模块的车位表(目前未交付)。
     * 待外部模块上线后填充。
     */
    @Schema(description = "场站与车位分布数据(地图渲染)。占位字段,待场站/车位模块上线后填充")
    private List<Map<String, Object>> stationSpaceList;

    /** 同上,占位字段,待外部模块上线后填充 */
    @Schema(description = "车位使用热力数据(热力图渲染)。占位字段,待场站模块上线后填充")
    private List<Map<String, Object>> heatMapData;

    @Schema(description = "查询成功率")
    private BigDecimal querySuccessRate;

    @Schema(description = "平均响应时长(毫秒)")
    private Integer avgResponseDuration;

}
