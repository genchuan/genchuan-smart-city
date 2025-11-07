package cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件列表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EventListRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4176")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "事件编号")
    @ExcelProperty("事件编号")
    private String eventNumber;

    @Schema(description = "事件发生时间")
    @ExcelProperty("事件发生时间")
    private LocalDateTime eventOccurrenceTime;

    @Schema(description = "事件发生地点")
    @ExcelProperty("事件发生地点")
    private String locationOfTheIncident;

    @Schema(description = "事件描述", example = "你猜")
    @ExcelProperty("事件描述")
    private String eventDescription;

    @Schema(description = "发现人")
    @ExcelProperty("发现人")
    private String discoverer;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}