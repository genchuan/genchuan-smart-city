package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 事件类型管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventTypeManagementPageReqVO extends PageParam {

    @Schema(description = "事件大类代码", example = "01")
    private String eventBigCode;

    @Schema(description = "事件大类名称", example = "公共设施")
    private String eventBigName;

    @Schema(description = "事件中类名称", example = "燃气设施")
    private String eventMidName;

    @Schema(description = "事件小类名称", example = "燃气泄漏")
    private String eventSmallName;

    @Schema(description = "启用状态", example = "启用")
    private String enableStatus;
}