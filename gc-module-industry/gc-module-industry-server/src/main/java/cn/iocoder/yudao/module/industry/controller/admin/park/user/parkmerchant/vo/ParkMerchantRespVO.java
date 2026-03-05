package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 商户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkMerchantRespVO {

    @Schema(description = "主键ID[商户唯一标识]", requiredMode = Schema.RequiredMode.REQUIRED, example = "10642")
    @ExcelProperty("主键ID[商户唯一标识]")
    private Long id;

    @Schema(description = "商户名称[唯一商户名称，如组织名称、公司名称等]", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("商户名称[唯一商户名称，如组织名称、公司名称等]")
    private String merchantName;

    @Schema(description = "商户编码[唯一商户编码]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("商户编码[唯一商户编码]")
    private String merchantCode;

    @Schema(description = "联系人姓名[商户对外对内的联系人姓名，可重名]")
    @ExcelProperty("联系人姓名[商户对外对内的联系人姓名，可重名]")
    private String contactPerson;

    @Schema(description = "联系电话[商户联系电话]")
    @ExcelProperty("联系电话[商户联系电话]")
    private String contactPhone;

    @Schema(description = "商户地址[商户经营或办公地址]")
    @ExcelProperty("商户地址[商户经营或办公地址]")
    private String address;

    @Schema(description = "经营范围[商户经营范围，如停车场运营/车辆进出管理]")
    @ExcelProperty("经营范围[商户经营范围，如停车场运营/车辆进出管理]")
    private String businessScope;

    @Schema(description = "状态[正常/停业/注销]", example = "1")
    @ExcelProperty("状态[正常/停业/注销]")
    private String status;

    @Schema(description = "分账比例[默认分账比例，百分比数值]")
    @ExcelProperty("分账比例[默认分账比例，百分比数值]")
    private BigDecimal settlementRatio;

    @Schema(description = "创建时间[记录创建时间]")
    @ExcelProperty("创建时间[记录创建时间]")
    private LocalDateTime createTime;

    @Schema(description = "创建人[数据创建人]")
    @ExcelProperty("创建人[数据创建人]")
    private String createBy;

    @Schema(description = "更新人[数据最后更新人]")
    @ExcelProperty("更新人[数据最后更新人]")
    private String updateBy;

    @Schema(description = "备注[商户相关备注说明]", example = "你说的对")
    @ExcelProperty("备注[商户相关备注说明]")
    private String remark;

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

}
