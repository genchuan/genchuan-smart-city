package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 事件办结归档新增/修改 Request VO")
@Data
public class EvtArchSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25269")
    private Long id;

    @Schema(description = "归档ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26984")
    @NotEmpty(message = "归档ID，唯一编码，UUID不能为空")
    private String archId;

    @Schema(description = "工单ID，关联事件工单表", requiredMode = Schema.RequiredMode.REQUIRED, example = "24777")
    @NotEmpty(message = "工单ID，关联事件工单表不能为空")
    private String woId;

    @Schema(description = "事件编码，关联事件分级分拨表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件编码，关联事件分级分拨表不能为空")
    private String evtCode;

    @Schema(description = "办结时间，格式yyyy - MM - dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "办结时间，格式yyyy - MM - dd HH:mm:ss不能为空")
    private LocalDateTime completeTime;

    @Schema(description = "办结说明")
    private String completeDesc;

}