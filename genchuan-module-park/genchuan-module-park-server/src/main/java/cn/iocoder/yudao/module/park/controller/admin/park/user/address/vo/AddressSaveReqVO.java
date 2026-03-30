package cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 地址新增/修改 Request VO")
@Data
public class AddressSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "5496")
    private Long id;

    @Schema(description = "[用户ID] 关联park_user.id，可为NULL", example = "27940")
    private Long userId;

    @Schema(description = "[企业ID] 关联park_enterprise_information.enterprise_id，可为NULL", example = "25420")
    private Long enterpriseId;

    @Schema(description = "[收货人姓名]", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "[收货人姓名]不能为空")
    private String receiverName;

    @Schema(description = "[联系电话]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[联系电话]不能为空")
    private String phone;

    @Schema(description = "[省份]")
    private String province;

    @Schema(description = "[城市]")
    private String city;

    @Schema(description = "[区县]")
    private String district;

    @Schema(description = "[详细地址]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[详细地址]不能为空")
    private String detailAddress;

    @Schema(description = "[是否默认地址] 如:0-否/1-是")
    private Boolean isDefault;

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
