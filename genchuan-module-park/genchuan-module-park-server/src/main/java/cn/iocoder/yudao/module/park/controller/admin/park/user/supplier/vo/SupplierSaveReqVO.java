package cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 供应商新增/修改 Request VO")
@Data
public class SupplierSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "457")
    private Long id;

    @Schema(description = "[供应商名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "[供应商名称]不能为空")
    private String supplierName;

    @Schema(description = "[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[地址]")
    private String address;

    @Schema(description = "[经营范围]")
    private String businessScope;

    @Schema(description = "[资质证明] JSON格式varchar")
    private String qualification;

    @Schema(description = "[状态] 如:合作中/暂停合作/已终止", example = "1")
    private String status;

    @Schema(description = "[备注]", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
