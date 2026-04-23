package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "巡查巡检 - 排班查看新增/修改 Request VO")
@Data
public class ScheduleViewSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "巡检人员ID不能为空")
    private Long userId;

    @Schema(description = "排班日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排班日期不能为空")
    private LocalDateTime scheduleDate;

    @Schema(description = "班次类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "班次类型不能为空")
    private String shiftType;

    @Schema(description = "排班状态")
    private String status;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}