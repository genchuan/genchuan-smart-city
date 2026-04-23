package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 离场记录新增/修改 Request VO")
@Data
public class LeaveRecordSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25996")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime enterTime;

    @Schema(description = "离场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离场时间不能为空")
    private LocalDateTime leaveTime;

    @Schema(description = "停车时长，单位：分钟，自动计算", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "停车时长，单位：分钟，自动计算不能为空")
    private Integer parkDuration;

    @Schema(description = "记录状态：正常记录/异常记录，关联字典：leave_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "记录状态：正常记录/异常记录，关联字典：leave_record_status不能为空")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "29836")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "佐证图片地址")
    private String proofImage;

    @Schema(description = "修正日志标记：0-未修正 1-已修正", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "修正日志标记：0-未修正 1-已修正不能为空")
    private Boolean isCorrected;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}