package cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 企业信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnterpriseInfoRespVO {

    @Schema(description = "[主键ID] 企业信息唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "16308")
    @ExcelProperty("[主键ID] 企业信息唯一标识")
    private Long id;

    @Schema(description = "[企业编码] 唯一编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[企业编码] 唯一编码")
    private String entCode;

    @Schema(description = "[企业名称] 企业全称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[企业名称] 企业全称")
    private String entName;

    @Schema(description = "[所属区域ID] 关联area_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24551")
    @ExcelProperty("[所属区域ID] 关联area_dict.id")
    private Long areaId;

    @Schema(description = "[地区名] 冗余的地区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[地区名] 冗余的地区名称")
    private String areaName;

    @Schema(description = "[企业类型ID] 关联ent_type_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28435")
    @ExcelProperty("[企业类型ID] 关联ent_type_dict.id")
    private Long entTypeId;

    @Schema(description = "[详细地址] 企业注册或经营地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[详细地址] 企业注册或经营地址")
    private String address;

    @Schema(description = "[联系人] 企业联系人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[联系人] 企业联系人姓名")
    private String contactPerson;

    @Schema(description = "[联系电话] 企业联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[联系电话] 企业联系电话")
    private String contactPhone;

    @Schema(description = "[企业经营状态] 如：正常/停业/注销", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[企业经营状态] 如：正常/停业/注销")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    @ExcelProperty("[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    @ExcelProperty("[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    @ExcelProperty("[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    @ExcelProperty("[通用扩展字段4] 预留")
    private String extCommon4;

}
