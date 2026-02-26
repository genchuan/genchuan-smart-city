package cn.iocoder.yudao.module.envirhealth.controller.admin.equipment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 设备新增/修改 Request VO")
@Data
public class EquipmentSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12119")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "14944")
    private String sysEquipmentId;

    @Schema(description = "设备名称", example = "赵六")
    private String name;

    @Schema(description = "设备编码")
    private String code;

    @Schema(description = "设备类型", example = "2")
    private String type;

    @Schema(description = "设备型号")
    private String model;

    @Schema(description = "规格参数")
    private String specification;

    @Schema(description = "维护周期（单位：天）")
    private Integer maintenanceCycle;

    @Schema(description = "状态：启用/禁用", example = "2")
    private Integer status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}