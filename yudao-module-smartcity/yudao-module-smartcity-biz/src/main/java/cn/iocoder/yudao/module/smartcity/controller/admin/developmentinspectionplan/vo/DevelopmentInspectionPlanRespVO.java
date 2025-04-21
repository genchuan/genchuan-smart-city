package cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查计划管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DevelopmentInspectionPlanRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24883")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "巡查时间")
    @ExcelProperty("巡查时间")
    private String patrolTime;

    @Schema(description = "区域")
    @ExcelProperty("区域")
    private String region;

    @Schema(description = "人员")
    @ExcelProperty("人员")
    private String staff;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}