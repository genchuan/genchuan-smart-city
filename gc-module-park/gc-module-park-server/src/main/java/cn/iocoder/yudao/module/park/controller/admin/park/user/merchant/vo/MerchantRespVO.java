package cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MerchantRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "32711")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[商户名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[商户名称]")
    private String merchantName;

    @Schema(description = "[商户编码] 唯一商户编码")
    @ExcelProperty("[商户编码] 唯一商户编码")
    private String merchantCode;

    @Schema(description = "[联系人]")
    @ExcelProperty("[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    @ExcelProperty("[联系电话]")
    private String contactPhone;

    @Schema(description = "[商户地址]")
    @ExcelProperty("[商户地址]")
    private String address;

    @Schema(description = "[经营范围]")
    @ExcelProperty("[经营范围]")
    private String businessScope;

    @Schema(description = "[区域编码] 关联park_area.area_code")
    @ExcelProperty("[区域编码] 关联park_area.area_code")
    private String regionCode;

    @Schema(description = "[统一社会信用代码]")
    @ExcelProperty("[统一社会信用代码]")
    private String creditCode;

    @Schema(description = "[状态] 如:正常/禁用/待审核/已驳回", example = "2")
    @ExcelProperty("[状态] 如:正常/禁用/待审核/已驳回")
    private String status;

    @Schema(description = "[结算账户]", example = "2425")
    @ExcelProperty("[结算账户]")
    private String settlementAccount;

    @Schema(description = "[入驻时间]")
    @ExcelProperty("[入驻时间]")
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
