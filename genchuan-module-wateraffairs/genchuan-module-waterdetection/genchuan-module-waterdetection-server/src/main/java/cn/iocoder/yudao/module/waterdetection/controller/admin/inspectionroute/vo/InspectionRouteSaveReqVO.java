package cn.iocoder.yudao.module.waterdetection.controller.admin.inspectionroute.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡检路线规划与优化新增/修改 Request VO")
@Data
public class InspectionRouteSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "路线ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "路线ID不能为空")
    private String routeId;

    @Schema(description = "巡检点ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "巡检点ID不能为空")
    private String inspectionPointId;

    @Schema(description = "巡检点类型(水源地/水厂/管网节点)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "巡检点类型(水源地/水厂/管网节点)不能为空")
    private String pointType;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "经度不能为空")
    private Double longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @Schema(description = "预计到达时间")
    private LocalDateTime estimatedArrivalTime;

    @Schema(description = "实际到达时间")
    private LocalDateTime actualArrivalTime;

    @Schema(description = "路线调整原因")
    private String routeAdjustReason;

}