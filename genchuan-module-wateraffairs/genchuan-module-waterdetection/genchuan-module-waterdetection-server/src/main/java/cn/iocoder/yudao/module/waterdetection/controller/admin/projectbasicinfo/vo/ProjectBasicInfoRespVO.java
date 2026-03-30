package cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 工程基本信息管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProjectBasicInfoRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "工程编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工程编码")
    private String projectCode;

    @Schema(description = "工程名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工程名称")
    private String projectName;

    @Schema(description = "设计供水规模(吨/日)")
    @ExcelProperty("设计供水规模(吨/日)")
    private String designCapacity;

    @Schema(description = "工艺类型")
    @ExcelProperty("工艺类型")
    private String processType;

    @Schema(description = "投产日期")
    @ExcelProperty("投产日期")
    private LocalDateTime commissioningDate;

    @Schema(description = "管理单位")
    @ExcelProperty("管理单位")
    private String managementUnit;

    @Schema(description = "工程状态")
    @ExcelProperty("工程状态")
    private String projectStatus;

    @Schema(description = "所属行政区")
    @ExcelProperty("所属行政区")
    private String administrativeRegion;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}