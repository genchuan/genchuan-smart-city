package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo;

import lombok.*;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 事件类型管理 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeManagementRespVO {

    @Schema(description = "主键ID", example = "1024")
    private Long id;

    @Schema(description = "事件类型ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String eventTypeId;

    @Schema(description = "事件大类代码", example = "01")
    private String eventBigCode;

    @Schema(description = "事件大类名称", example = "公共设施类")
    private String eventBigName;

    @Schema(description = "事件中类代码", example = "0101")
    private String eventMidCode;

    @Schema(description = "事件中类名称", example = "燃气设施")
    private String eventMidName;

    @Schema(description = "事件小类代码", example = "010101")
    private String eventSmallCode;

    @Schema(description = "事件小类名称", example = "燃气泄漏")
    private String eventSmallName;

    @Schema(description = "事件类型描述", example = "燃气泄漏：指燃气管道、阀门等部件泄漏导致的事件")
    private String eventTypeDesc;

    @Schema(description = "启用状态", example = "启用")
    private String enableStatus;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    private String createUser;
}