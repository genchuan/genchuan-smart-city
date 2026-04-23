package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 换班申请新增/修改 Request VO")
@Data
public class ShiftApplySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "申请人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请人ID不能为空")
    private Long applyUserId;

    @Schema(description = "换班对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "换班对象ID不能为空")
    private Long targetUserId;

    @Schema(description = "原日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "原日期不能为空")
    private LocalDateTime oldDate;

    @Schema(description = "新日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "新日期不能为空")
    private LocalDateTime newDate;

    @Schema(description = "申请状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申请状态不能为空")
    private String status;

    @Schema(description = "审核人ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}