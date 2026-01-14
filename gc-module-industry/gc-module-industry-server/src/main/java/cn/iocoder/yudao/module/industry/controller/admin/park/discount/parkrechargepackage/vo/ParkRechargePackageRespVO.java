package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 充值套餐 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkRechargePackageRespVO {

    @Schema(description = "[主键ID] 充值套餐唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "25504")
    @ExcelProperty("[主键ID] 充值套餐唯一标识")
    private Long id;

    @Schema(description = "[套餐名称] 充值套餐名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[套餐名称] 充值套餐名称")
    private String packageName;

    @Schema(description = "[充值金额] 实际充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[充值金额] 实际充值金额")
    private BigDecimal rechargeAmount;

    @Schema(description = "[赠送金额] 充值赠送金额")
    @ExcelProperty("[赠送金额] 充值赠送金额")
    private BigDecimal giveAmount;

    @Schema(description = "[赠送时间] 赠送时间，单位分钟")
    @ExcelProperty("[赠送时间] 赠送时间，单位分钟")
    private Integer giveTime;

    @Schema(description = "[状态] 如:上架/下架", example = "2")
    @ExcelProperty("[状态] 如:上架/下架")
    private String status;

    @Schema(description = "[销售数量] 套餐销售数量", example = "29325")
    @ExcelProperty("[销售数量] 套餐销售数量")
    private Integer salesCount;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 充值套餐相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 充值套餐相关备注说明")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
