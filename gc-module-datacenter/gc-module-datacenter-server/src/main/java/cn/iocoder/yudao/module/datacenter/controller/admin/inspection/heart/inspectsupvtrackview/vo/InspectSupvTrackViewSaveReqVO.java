package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检督办进度跟踪视图新增/修改 Request VO")
@Data
public class InspectSupvTrackViewSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "督办任务ID")
    private String supvTaskId;

    @Schema(description = "督办任务编码")
    private String supvTaskCode;

    @Schema(description = "关联问题名称")
    private String problemName;

    @Schema(description = "督办人姓名")
    private String supvUserName;

    @Schema(description = "整改负责人姓名")
    private String leaderUserName;

    @Schema(description = "督办任务状态")
    private String supvStatus;

    @Schema(description = "督办截止时间")
    private LocalDateTime supvDeadlineTime;

    @Schema(description = "是否超期")
    private Boolean isOverdue;

    @Schema(description = "最新督办进展时间")
    private LocalDateTime latestSupvTime;

    @Schema(description = "最新督办进展类型")
    private String latestSupvType;

    @Schema(description = "最新督办进展内容")
    private String latestSupvContent;

    @Schema(description = "督办操作次数")
    private Integer supvOperCount;

    @Schema(description = "关联整改任务状态")
    private String rectifyStatus;

}