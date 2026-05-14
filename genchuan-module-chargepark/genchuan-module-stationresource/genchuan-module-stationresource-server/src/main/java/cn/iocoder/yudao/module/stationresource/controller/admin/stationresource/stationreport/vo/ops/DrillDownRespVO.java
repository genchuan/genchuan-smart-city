package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 场站资源报表 — 钻取响应 VO
 *
 * @author vrvliang
 * @version V1.0 2026-05-11 10:07
 */
@Schema(description = "场站资源报表 — 钻取响应")
@Data
public class DrillDownRespVO {

    @Schema(description = "卡片指标", example = "totalAreaCount")
    private String metric;

    @Schema(description = "指标中文名", example = "总片区数")
    private String metricName;

    @Schema(description = "总条数", example = "16")
    private Long total;

    @Schema(description = "钻取数据列表")
    private List<Map<String, Object>> list;


}
