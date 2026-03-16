package cn.iocoder.yudao.module.envirhealth.controller.admin.institutiontype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 机构类型字典新增/修改 Request VO")
@Data
public class InstitutionTypeSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19407")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "21166")
    private String sysInstitutionTypeId;

    @Schema(description = "类型名称（可选值：学校/医院/政府机关/公园/图书馆/体育馆/博物馆/车站）", example = "李四")
    private String name;

    @Schema(description = "类型编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "随便")
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