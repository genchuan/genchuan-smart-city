package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 商户权限 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkMerchantPermissionRespVO {

    @Schema(description = "主键ID[商户权限唯一标识]", requiredMode = Schema.RequiredMode.REQUIRED, example = "12260")
    @ExcelProperty("主键ID[商户权限唯一标识]")
    private Long id;

    @Schema(description = "商户ID[关联商户ID，关联 park_merchant 表]", requiredMode = Schema.RequiredMode.REQUIRED, example = "5753")
    @ExcelProperty("商户ID[关联商户ID，关联 park_merchant 表]")
    private Long merchantId;

    @Schema(description = "权限编码[关联 park_permission.perm_code]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("权限编码[关联 park_permission.perm_code]")
    private String permCode;

    @Schema(description = "权限状态[启用/禁用]", example = "2")
    @ExcelProperty("权限状态[启用/禁用]")
    private String status;

    @Schema(description = "创建时间[记录创建时间]")
    @ExcelProperty("创建时间[记录创建时间]")
    private LocalDateTime createTime;

    @Schema(description = "通用扩展字段1[预留扩展字段]")
    @ExcelProperty("通用扩展字段1[预留扩展字段]")
    private String extCommon1;

    @Schema(description = "通用扩展字段2[预留扩展字段]")
    @ExcelProperty("通用扩展字段2[预留扩展字段]")
    private String extCommon2;

    @Schema(description = "通用扩展字段3[预留扩展字段]")
    @ExcelProperty("通用扩展字段3[预留扩展字段]")
    private String extCommon3;

    @Schema(description = "通用扩展字段4[预留扩展字段]")
    @ExcelProperty("通用扩展字段4[预留扩展字段]")
    private String extCommon4;

    @Schema(description = "备注[权限相关备注说明]", example = "随便")
    @ExcelProperty("备注[权限相关备注说明]")
    private String remark;

}
