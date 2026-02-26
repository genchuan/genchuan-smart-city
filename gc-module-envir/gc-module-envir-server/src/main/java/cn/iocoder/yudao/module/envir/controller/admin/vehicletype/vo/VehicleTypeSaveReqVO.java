package cn.iocoder.yudao.module.envir.controller.admin.vehicletype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 车辆类型字典新增/修改 Request VO")
@Data
public class VehicleTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16294")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "969")
    private String sysVehicleTypeId;

    @Schema(description = "类型名称（可选值：清运车/清扫车/洒水车/洗扫车/垃圾转运车/吸污车/巡查车）", example = "王五")
    private String name;

    @Schema(description = "类型编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    private Integer status;

    @Schema(description = "类型描述", example = "随便")
    private String description;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}