package cn.iocoder.yudao.module.park.controller.admin.park.user.supplier.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 供应商 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SupplierRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "457")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[供应商名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("[供应商名称]")
    private String supplierName;

    @Schema(description = "[联系人]")
    @ExcelProperty("[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    @ExcelProperty("[联系电话]")
    private String contactPhone;

    @Schema(description = "[地址]")
    @ExcelProperty("[地址]")
    private String address;

    @Schema(description = "[经营范围]")
    @ExcelProperty("[经营范围]")
    private String businessScope;

    @Schema(description = "[资质证明] JSON格式varchar")
    @ExcelProperty("[资质证明] JSON格式varchar")
    private String qualification;

    @Schema(description = "[状态] 如:合作中/暂停合作/已终止", example = "1")
    @ExcelProperty("[状态] 如:合作中/暂停合作/已终止")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
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
