package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.facility.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 设施字典新增/修改 Request VO")
@Data
public class FacilitySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19902")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "2644")
    private String sysFacilityId;

    @Schema(description = "设施名称", example = "李四")
    private String name;

    @Schema(description = "设施编码")
    private String code;

    @Schema(description = "设施类型", example = "1")
    private String type;

    @Schema(description = "状态：启用/禁用", example = "2")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
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