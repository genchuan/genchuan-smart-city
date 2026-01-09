package cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 事件列表新增/修改 Request VO")
@Data
public class EventListSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4176")
    private Long id;

    @Schema(description = "事件编号")
    private String eventNumber;

    @Schema(description = "事件发生时间")
    private LocalDateTime eventOccurrenceTime;

    @Schema(description = "事件发生地点")
    private String locationOfTheIncident;

    @Schema(description = "事件描述", example = "你猜")
    private String eventDescription;

    @Schema(description = "发现人")
    private String discoverer;

    @Schema(description = "备注")
    private String notes;

}