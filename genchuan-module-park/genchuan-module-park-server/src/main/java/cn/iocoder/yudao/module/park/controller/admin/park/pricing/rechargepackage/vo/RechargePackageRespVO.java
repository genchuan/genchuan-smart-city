package cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 充值套餐 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RechargePackageRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "28209")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[套餐名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[套餐名称]")
    private String packageName;

    @Schema(description = "[充值金额]")
    @ExcelProperty("[充值金额]")
    private BigDecimal rechargeAmount;

    @Schema(description = "[赠送金额/时长] JSON格式varchar")
    @ExcelProperty("[赠送金额/时长] JSON格式varchar")
    private String giveAmount;

    @Schema(description = "[赠送内容有效期] 可为NULL")
    @ExcelProperty("[赠送内容有效期] 可为NULL")
    private Integer validDays;

    @Schema(description = "[套餐类型] 如:金额套餐/时长套餐", example = "1")
    @ExcelProperty("[套餐类型] 如:金额套餐/时长套餐")
    private String packageType;

    @Schema(description = "[状态] 如:上架/下架", example = "2")
    @ExcelProperty("[状态] 如:上架/下架")
    private String status;

    @Schema(description = "[销量]", example = "30218")
    @ExcelProperty("[销量]")
    private Integer salesCount;

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
