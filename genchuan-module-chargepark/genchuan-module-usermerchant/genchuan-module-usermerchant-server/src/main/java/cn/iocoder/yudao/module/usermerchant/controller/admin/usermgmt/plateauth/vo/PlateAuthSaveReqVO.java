package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车牌认证新增/修改 Request VO")
@Data
public class PlateAuthSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "所属用户ID，关联 user_info 表 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属用户ID，关联 user_info 表 id不能为空")
    private Long userId;

    @Schema(description = "关联车辆ID，关联 user_car 表 id", example = "1")
    private Long carId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽 C12345")
    @NotEmpty(message = "车牌号码不能为空")
    private String plateNo;

    @Schema(description = "行驶证图片地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "/genchuan/chargePark/userMerchant/driving/123.jpg")
    @NotEmpty(message = "行驶证图片地址不能为空")
    private String drivingLicense;

    @Schema(description = "认证申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "认证申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "认证状态：待审核/已认证/已驳回，关联芋道字典表 plate_auth_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "待审核")
    @NotEmpty(message = "认证状态：待审核/已认证/已驳回，关联芋道字典表 plate_auth_status不能为空")
    private String status;

    @Schema(description = "审核人ID，关联芋道用户表 system_user", example = "1")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注")
    private String auditRemark;

    @Schema(description = "备注", example = "新车认证申请")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}