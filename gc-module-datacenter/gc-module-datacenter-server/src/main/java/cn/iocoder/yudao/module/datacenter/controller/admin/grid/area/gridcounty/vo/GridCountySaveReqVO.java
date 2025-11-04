package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 县级及以上行政区划配置新增/修改 Request VO")
@Data
public class GridCountySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "行政区划ID")
    private String adminDivisionId;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String adminCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String adminName;

    @Schema(description = "行政区划级别(1-省级,2-市级,3-县级)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "行政区划级别(1-省级,2-市级,3-县级)不能为空")
    private Integer adminLevel;

    @Schema(description = "上级行政区划ID")
    private String parentAdminId;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    private LocalDateTime invalidTime;

    @Schema(description = "备注")
    private String remark;

}