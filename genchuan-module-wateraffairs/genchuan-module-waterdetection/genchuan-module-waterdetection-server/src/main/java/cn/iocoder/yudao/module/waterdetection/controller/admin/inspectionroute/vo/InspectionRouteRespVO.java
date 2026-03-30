package cn.iocoder.yudao.module.waterdetection.controller.admin.inspectionroute.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 巡检路线规划与优化 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionRouteRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "路线ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("路线ID")
    private String routeId;

    @Schema(description = "巡检点ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡检点ID")
    private String inspectionPointId;

    @Schema(description = "巡检点类型(水源地/水厂/管网节点)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("巡检点类型(水源地/水厂/管网节点)")
    private String pointType;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经度")
    private Double longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("纬度")
    private Double latitude;

    @Schema(description = "预计到达时间")
    @ExcelProperty("预计到达时间")
    private LocalDateTime estimatedArrivalTime;

    @Schema(description = "实际到达时间")
    @ExcelProperty("实际到达时间")
    private LocalDateTime actualArrivalTime;

    @Schema(description = "路线调整原因")
    @ExcelProperty("路线调整原因")
    private String routeAdjustReason;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}