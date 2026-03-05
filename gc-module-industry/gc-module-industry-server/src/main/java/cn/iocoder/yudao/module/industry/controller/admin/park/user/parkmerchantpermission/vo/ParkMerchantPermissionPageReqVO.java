package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchantpermission.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 商户权限分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkMerchantPermissionPageReqVO extends PageParam {

    @Schema(description = "商户ID[关联商户ID，关联 park_merchant 表]", example = "5753")
    private Long merchantId;

    @Schema(description = "权限编码[关联 park_permission.perm_code]")
    private String permCode;

    @Schema(description = "权限状态[启用/禁用]", example = "2")
    private String status;

    @Schema(description = "创建时间[记录创建时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "通用扩展字段1[预留扩展字段]")
    private String extCommon1;

    @Schema(description = "通用扩展字段2[预留扩展字段]")
    private String extCommon2;

    @Schema(description = "通用扩展字段3[预留扩展字段]")
    private String extCommon3;

    @Schema(description = "通用扩展字段4[预留扩展字段]")
    private String extCommon4;

    @Schema(description = "备注[权限相关备注说明]", example = "随便")
    private String remark;

}
