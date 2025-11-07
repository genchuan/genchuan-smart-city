package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件办结归档 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvtArchRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25269")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "归档ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26984")
    @ExcelProperty("归档ID，唯一编码，UUID")
    private String archId;

    @Schema(description = "工单ID，关联事件工单表", requiredMode = Schema.RequiredMode.REQUIRED, example = "24777")
    @ExcelProperty("工单ID，关联事件工单表")
    private String woId;

    @Schema(description = "事件编码，关联事件分级分拨表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事件编码，关联事件分级分拨表")
    private String evtCode;

    @Schema(description = "办结时间，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("办结时间，格式yyyy - MM - dd HH:mm:ss")
    private LocalDateTime completeTime;

    @Schema(description = "办结说明")
    @ExcelProperty("办结说明")
    private String completeDesc;

}