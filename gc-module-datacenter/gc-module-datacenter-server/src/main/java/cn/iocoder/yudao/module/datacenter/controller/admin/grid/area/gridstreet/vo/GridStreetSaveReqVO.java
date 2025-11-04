package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 街道（镇、乡）行政区划配置新增/修改 Request VO")
@Data
public class GridStreetSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "街道ID")
    private String streetId;

    @Schema(description = "街道代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "街道代码不能为空")
    private String streetCode;

    @Schema(description = "街道名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "街道名称不能为空")
    private String streetName;

    @Schema(description = "所属县级行政区ID")
    private String countyAdminId;

    @Schema(description = "街道级别(1-街道,2-镇,3-乡)")
    private Integer streetLevel;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    private LocalDateTime invalidTime;

    @Schema(description = "备注")
    private String remark;

}