package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 坐标系配置新增/修改 Request VO")
@Data
public class GeocodingCoordinateSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "坐标系ID")
    private String coordinateId;

    @Schema(description = "坐标系名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "坐标系名称不能为空")
    private String coordinateName;

    @Schema(description = "坐标系代码")
    private String coordinateCode;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    private String configUser;

    @Schema(description = "配置时间")
    private LocalDateTime configTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    private String extCategory3;

}