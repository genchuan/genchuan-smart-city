package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件处置跟踪 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvtDisposalTrackRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18925")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "跟踪ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3982")
    @ExcelProperty("跟踪ID，唯一编码，UUID")
    private String trackId;

    @Schema(description = "工单ID，关联事件工单表", requiredMode = Schema.RequiredMode.REQUIRED, example = "20358")
    @ExcelProperty("工单ID，关联事件工单表")
    private String woId;

    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("处置状态")
    private String handleStatus;

    @Schema(description = "当前处置节点", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前处置节点")
    private String currentNode;

    @Schema(description = "处置人员", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("处置人员")
    private String handlerName;

    @Schema(description = "处置开始时间，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置开始时间，格式yyyy - MM - dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "预计完成时间，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预计完成时间，格式yyyy - MM - dd HH:mm:ss")
    private LocalDateTime estCompleteTime;

}