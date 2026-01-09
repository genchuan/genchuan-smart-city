package cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件处理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EventProcessingRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "879")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "事件处理编号")
    @ExcelProperty("事件处理编号")
    private String eventHandlingNumber;

    @Schema(description = "关联事件上报编号", example = "31517")
    @ExcelProperty("关联事件上报编号")
    private String relatedEventReportingId;

    @Schema(description = "处理部门")
    @ExcelProperty("处理部门")
    private String processingDepartment;

    @Schema(description = "处理人员")
    @ExcelProperty("处理人员")
    private String processingPersonnel;

    @Schema(description = "接收时间")
    @ExcelProperty("接收时间")
    private String receptionTime;

    @Schema(description = "处理措施")
    @ExcelProperty("处理措施")
    private String handlingMeasures;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}