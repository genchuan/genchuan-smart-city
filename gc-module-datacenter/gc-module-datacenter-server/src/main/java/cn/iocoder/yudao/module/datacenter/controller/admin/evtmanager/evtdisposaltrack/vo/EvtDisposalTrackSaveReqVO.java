package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 事件处置跟踪新增/修改 Request VO")
@Data
public class EvtDisposalTrackSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18925")
    private Long id;

    @Schema(description = "跟踪ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3982")
    @NotEmpty(message = "跟踪ID，唯一编码，UUID不能为空")
    private String trackId;

    @Schema(description = "工单ID，关联事件工单表", requiredMode = Schema.RequiredMode.REQUIRED, example = "20358")
    @NotEmpty(message = "工单ID，关联事件工单表不能为空")
    private String woId;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "处置状态不能为空")
    private String handleStatus;

    @Schema(description = "当前处置节点", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "当前处置节点不能为空")
    private String currentNode;

    @Schema(description = "处置人员", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "处置人员不能为空")
    private String handlerName;

    @Schema(description = "处置开始时间，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "处置开始时间，格式yyyy - MM - dd HH:mm:ss不能为空")
    private LocalDateTime startTime;

    @Schema(description = "预计完成时间，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预计完成时间，格式yyyy - MM - dd HH:mm:ss不能为空")
    private LocalDateTime estCompleteTime;

}