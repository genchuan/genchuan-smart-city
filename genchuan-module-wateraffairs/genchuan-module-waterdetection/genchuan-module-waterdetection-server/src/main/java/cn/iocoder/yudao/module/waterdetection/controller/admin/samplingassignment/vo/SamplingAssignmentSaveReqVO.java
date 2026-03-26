package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 采样人员分配新增/修改 Request VO")
@Data
public class SamplingAssignmentSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "采样计划编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "采样计划编号不能为空")
    private String planCode;

    @Schema(description = "采样点清单", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "采样点清单不能为空")
    private String pointList;

    @Schema(description = "负责人员", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "负责人员不能为空")
    private String responsiblePerson;

    @Schema(description = "分配时间")
    private LocalDateTime assignTime;

    @Schema(description = "完成时限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "完成时限不能为空")
    private LocalDateTime deadline;

    @Schema(description = "联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系方式不能为空")
    private String contactInfo;

}