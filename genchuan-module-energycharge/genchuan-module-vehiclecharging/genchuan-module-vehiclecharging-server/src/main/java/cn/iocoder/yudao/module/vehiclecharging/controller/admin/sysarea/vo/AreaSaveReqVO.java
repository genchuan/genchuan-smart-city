package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 统一行政区划配置表（树形结构）新增/修改 Request VO")
@Data
public class AreaSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16511")
    private Long id;

    @Schema(description = "上级行政区划ID（0表示根节点）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27543")
    @NotNull(message = "上级行政区划ID（0表示根节点）不能为空")
    private Long parentId;

    @Schema(description = "完整行政区划代码（12位）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "完整行政区划代码（12位）不能为空")
    private String fullCode;

    @Schema(description = "短代码（省/市/县6位，乡镇/社区3位）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "短代码（省/市/县6位，乡镇/社区3位）不能为空")
    private String shortCode;

    @Schema(description = "社区ID", example = "12571")
    private String commId;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "行政区划名称不能为空")
    private String name;

    @Schema(description = "层级：1-省级 2-市级 3-县级 4-乡镇 5-社区", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "层级：1-省级 2-市级 3-县级 4-乡镇 5-社区不能为空")
    private Integer level;

    @Schema(description = "类型（街道/镇/乡/社区/村）", example = "1")
    private String areaType;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间")
    private LocalDateTime invalidTime;

    @Schema(description = "社区边界坐标（2000坐标系）")
    private String boundary;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}