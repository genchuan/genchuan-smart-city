package cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 油车占位监测新增/修改 Request VO")
@Data
public class OilMonitorSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "车位ID不能为空")
    private Long spaceId;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "识别时间不能为空")
    private LocalDateTime identifyTime;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置状态不能为空")
    private String processStatus;

    @Schema(description = "处置人ID")
    private Long processUserId;

    @Schema(description = "处置时间")
    private LocalDateTime processTime;

    @Schema(description = "忽略理由")
    private String ignoreReason;

    @Schema(description = "处置进度")
    private Integer processProgress;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}