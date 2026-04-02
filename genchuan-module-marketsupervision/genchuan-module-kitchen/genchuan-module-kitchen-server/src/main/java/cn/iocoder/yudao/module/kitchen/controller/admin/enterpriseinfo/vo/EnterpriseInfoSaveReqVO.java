package cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 企业信息新增/修改 Request VO")
@Data
public class EnterpriseInfoSaveReqVO {

    @Schema(description = "[主键ID] 企业信息唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "16308")
    private Long id;

    @Schema(description = "[企业编码] 唯一编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[企业编码] 唯一编码不能为空")
    private String entCode;

    @Schema(description = "[企业名称] 企业全称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "[企业名称] 企业全称不能为空")
    private String entName;

    @Schema(description = "[所属区域ID] 关联area_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24551")
    @NotNull(message = "[所属区域ID] 关联area_dict.id不能为空")
    private Long areaId;

    @Schema(description = "[地区名] 冗余的地区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[地区名] 冗余的地区名称不能为空")
    private String areaName;

    @Schema(description = "[企业类型ID] 关联ent_type_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28435")
    @NotNull(message = "[企业类型ID] 关联ent_type_dict.id不能为空")
    private Long entTypeId;

    @Schema(description = "[企业类型名称]", example = "食堂")
    @NotNull(message = "[企业类型名称]")
    private String entTypeName;

    @Schema(description = "[详细地址] 企业注册或经营地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[详细地址] 企业注册或经营地址不能为空")
    private String address;

    @Schema(description = "[联系人] 企业联系人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[联系人] 企业联系人姓名不能为空")
    private String contactPerson;

    @Schema(description = "[联系电话] 企业联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[联系电话] 企业联系电话不能为空")
    private String contactPhone;

    @Schema(description = "[企业经营状态] 如：正常/停业/注销", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[企业经营状态] 如：正常/停业/注销不能为空")
    private String status;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
