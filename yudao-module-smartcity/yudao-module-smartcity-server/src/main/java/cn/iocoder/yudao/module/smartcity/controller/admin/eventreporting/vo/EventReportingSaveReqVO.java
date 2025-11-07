package cn.iocoder.yudao.module.smartcity.controller.admin.eventreporting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 事件上报新增/修改 Request VO")
@Data
public class EventReportingSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "23460")
    private Long id;

    @Schema(description = "事件编号")
    private String eventNumber;

    @Schema(description = "事件名称", example = "芋艿")
    private String eventName;

    @Schema(description = "事件类型", example = "1")
    private String eventType;

    @Schema(description = "事件描述", example = "你说的对")
    private String eventDescription;

    @Schema(description = "发生时间")
    private LocalDateTime occurrenceTime;

    @Schema(description = "发生地点")
    private String place;

    @Schema(description = "经纬度坐标")
    private String latitudeLongitude;

    @Schema(description = "上报人")
    private String reportperson;

}