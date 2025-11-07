package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 事件类型管理基础 Request VO")
@Data
public class EventTypeManagementBaseVO {

    @Schema(description = "事件大类代码，01-99", requiredMode = Schema.RequiredMode.REQUIRED, example = "01")
    @NotBlank(message = "事件大类代码不能为空")
    @Size(min = 2, max = 2, message = "事件大类代码长度必须为2位")
    private String eventBigCode;

    @Schema(description = "事件大类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "公共设施类")
    @NotBlank(message = "事件大类名称不能为空")
    @Size(max = 50, message = "事件大类名称长度不能超过50个字符")
    private String eventBigName;

    @Schema(description = "事件中类代码，0101-9999", example = "0101")
    @Size(min = 4, max = 4, message = "事件中类代码长度必须为4位")
    private String eventMidCode;

    @Schema(description = "事件中类名称", example = "燃气设施")
    @Size(max = 50, message = "事件中类名称长度不能超过50个字符")
    private String eventMidName;

    @Schema(description = "事件小类代码，010101-999999", example = "010101")
    @Size(min = 6, max = 6, message = "事件小类代码长度必须为6位")
    private String eventSmallCode;

    @Schema(description = "事件小类名称", example = "燃气泄漏")
    @Size(max = 50, message = "事件小类名称长度不能超过50个字符")
    private String eventSmallName;

    @Schema(description = "事件类型描述", example = "燃气泄漏：指燃气管道、阀门等部件泄漏导致的事件")
    @Size(max = 200, message = "事件类型描述长度不能超过200个字符")
    private String eventTypeDesc;

    @Schema(description = "启用状态，启用/禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "启用")
    @NotBlank(message = "启用状态不能为空")
    private String enableStatus;
}