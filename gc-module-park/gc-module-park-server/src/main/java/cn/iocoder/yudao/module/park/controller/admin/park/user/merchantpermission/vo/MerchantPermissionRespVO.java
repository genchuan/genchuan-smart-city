package cn.iocoder.yudao.module.park.controller.admin.park.user.merchantpermission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 商户权限 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MerchantPermissionRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "24644")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[商户ID] 关联park_merchant.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11773")
    @ExcelProperty("[商户ID] 关联park_merchant.id")
    private Long merchantId;

    @Schema(description = "[权限ID] 关联park_permission.perm_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15514")
    @ExcelProperty("[权限ID] 关联park_permission.perm_id")
    private Long permId;

    @Schema(description = "[权限编码]")
    @ExcelProperty("[权限编码]")
    private String permCode;

    @Schema(description = "[权限名称]", example = "芋艿")
    @ExcelProperty("[权限名称]")
    private String permName;

    @Schema(description = "[生效时间]")
    @ExcelProperty("[生效时间]")
    private LocalDateTime effectTime;

    @Schema(description = "[失效时间] 永久有效为NULL")
    @ExcelProperty("[失效时间] 永久有效为NULL")
    private LocalDateTime expireTime;

    @Schema(description = "[权限状态] 如:启用/禁用", example = "1")
    @ExcelProperty("[权限状态] 如:启用/禁用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "你猜")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
