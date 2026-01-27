package cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户权限新增/修改 Request VO")
@Data
public class MerchantPermissionSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "24644")
    private Long id;

    @Schema(description = "[商户ID] 关联park_merchant.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11773")
    @NotNull(message = "[商户ID] 关联park_merchant.id不能为空")
    private Long merchantId;

    @Schema(description = "[权限ID] 关联park_permission.perm_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15514")
    @NotNull(message = "[权限ID] 关联park_permission.perm_id不能为空")
    private Long permId;

    @Schema(description = "[权限编码]")
    private String permCode;

    @Schema(description = "[权限名称]", example = "芋艿")
    private String permName;

    @Schema(description = "[生效时间]")
    private LocalDateTime effectTime;

    @Schema(description = "[失效时间] 永久有效为NULL")
    private LocalDateTime expireTime;

    @Schema(description = "[权限状态] 如:启用/禁用", example = "1")
    private String status;

    @Schema(description = "[备注]", example = "你猜")
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
