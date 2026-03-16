package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 垃圾品类字典新增/修改 Request VO")
@Data
public class GarbageTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12817")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "25568")
    private String sysGarbageTypeId;

    @Schema(description = "品类名称", example = "王五")
    private String name;

    @Schema(description = "品类编码")
    private String code;

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