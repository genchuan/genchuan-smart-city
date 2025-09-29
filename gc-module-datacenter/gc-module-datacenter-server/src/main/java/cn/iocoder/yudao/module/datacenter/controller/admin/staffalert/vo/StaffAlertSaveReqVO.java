package cn.iocoder.yudao.module.datacenter.controller.admin.staffalert.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 人员异常报警新增/修改 Request VO")
@Data
public class StaffAlertSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "报警ID")
    private String alertId;

    @Schema(description = "人员ID")
    private String staffId;

    @Schema(description = "人员姓名")
    private String staffName;

    @Schema(description = "报警类型")
    private String alertType;

    @Schema(description = "报警描述")
    private String alertDescription;

    @Schema(description = "报警时间")
    private LocalDateTime alertTime;

    @Schema(description = "报警级别")
    private String alertLevel;

    @Schema(description = "处理状态")
    private String processStatus;

    @Schema(description = "处理人员ID")
    private String processorId;

    @Schema(description = "处理人员姓名")
    private String processorName;

    @Schema(description = "处理措施")
    private String processMeasure;

    @Schema(description = "处理时间")
    private LocalDateTime processTime;

    @Schema(description = "处理结果")
    private String processResult;

}