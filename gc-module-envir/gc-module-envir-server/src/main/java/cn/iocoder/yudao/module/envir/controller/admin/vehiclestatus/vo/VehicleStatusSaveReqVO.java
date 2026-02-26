package cn.iocoder.yudao.module.envir.controller.admin.vehiclestatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 车辆状态字典新增/修改 Request VO")
@Data
public class VehicleStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32519")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "7674")
    private String sysVehicleStatusId;

    @Schema(description = "状态名称（可选值：正常运行/维护中/故障/闲置/报废/待年检/停运）", example = "赵六")
    private String name;

    @Schema(description = "状态编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    private Integer status;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}