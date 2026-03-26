package cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 工程基本信息管理新增/修改 Request VO")
@Data
public class ProjectBasicInfoSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "工程编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工程编码不能为空")
    private String projectCode;

    @Schema(description = "工程名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工程名称不能为空")
    private String projectName;

    @Schema(description = "设计供水规模(吨/日)")
    private String designCapacity;

    @Schema(description = "工艺类型")
    private String processType;

    @Schema(description = "投产日期")
    private LocalDateTime commissioningDate;

    @Schema(description = "管理单位")
    private String managementUnit;

    @Schema(description = "工程状态")
    private String projectStatus;

    @Schema(description = "所属行政区")
    private String administrativeRegion;

}