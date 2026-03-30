package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 采样人员分配 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SamplingAssignmentRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "采样计划编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样计划编号")
    private String planCode;

    @Schema(description = "采样点清单", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样点清单")
    private String pointList;

    @Schema(description = "负责人员", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("负责人员")
    private String responsiblePerson;

    @Schema(description = "分配时间")
    @ExcelProperty("分配时间")
    private LocalDateTime assignTime;

    @Schema(description = "完成时限", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("完成时限")
    private LocalDateTime deadline;

    @Schema(description = "联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系方式")
    private String contactInfo;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}