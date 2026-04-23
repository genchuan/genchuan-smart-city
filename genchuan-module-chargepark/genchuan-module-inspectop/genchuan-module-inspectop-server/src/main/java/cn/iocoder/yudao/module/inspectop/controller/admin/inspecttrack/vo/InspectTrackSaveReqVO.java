package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 巡检轨迹新增/修改 Request VO")
@Data
public class InspectTrackSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "巡检人员ID不能为空")
    private Long userId;

    @Schema(description = "轨迹时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "轨迹时间不能为空")
    private LocalDateTime trackTime;

    @Schema(description = "巡检里程（公里）")
    private BigDecimal mileage;

    @Schema(description = "巡检时长（分钟）")
    private Integer duration;

    @Schema(description = "所属片区")
    private String area;

    @Schema(description = "轨迹状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "轨迹状态不能为空")
    private String status;

    @Schema(description = "轨迹点")
    private String points;

    @Schema(description = "核查状态：0-未核查 1-已核查 2-核查中")
    private Integer checkStatus;

    @Schema(description = "核查备注")
    private String checkRemark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}