package cn.iocoder.yudao.module.waterdetection.controller.admin.issuetracking.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 问题上报与闭环跟踪新增/修改 Request VO")
@Data
public class IssueTrackingSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "问题ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问题ID不能为空")
    private String issueId;

    @Schema(description = "问题类型(漏点/设备故障/标识牌损坏)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问题类型(漏点/设备故障/标识牌损坏)不能为空")
    private String issueType;

    @Schema(description = "上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报时间不能为空")
    private LocalDateTime reportTime;

    @Schema(description = "派单时间")
    private LocalDateTime dispatchTime;

    @Schema(description = "维修人员ID")
    private String repairStaffId;

    @Schema(description = "修复时间")
    private LocalDateTime repairTime;

    @Schema(description = "验收结果")
    private String inspectionResult;

    @Schema(description = "闭环状态")
    private String closureStatus;

}