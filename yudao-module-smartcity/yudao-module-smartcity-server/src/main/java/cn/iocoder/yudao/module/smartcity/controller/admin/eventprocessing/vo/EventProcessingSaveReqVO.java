package cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 事件处理新增/修改 Request VO")
@Data
public class EventProcessingSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "879")
    private Long id;

    @Schema(description = "事件处理编号")
    private String eventHandlingNumber;

    @Schema(description = "关联事件上报编号", example = "31517")
    private String relatedEventReportingId;

    @Schema(description = "处理部门")
    private String processingDepartment;

    @Schema(description = "处理人员")
    private String processingPersonnel;

    @Schema(description = "接收时间")
    private String receptionTime;

    @Schema(description = "处理措施")
    private String handlingMeasures;

}