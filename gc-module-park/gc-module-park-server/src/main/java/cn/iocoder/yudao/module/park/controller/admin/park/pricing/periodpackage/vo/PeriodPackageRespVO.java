package cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 期卡套餐 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PeriodPackageRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "26465")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[套餐名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[套餐名称]")
    private String packageName;

    @Schema(description = "[套餐类型] 如:日卡/周卡/月卡/季卡/年卡", example = "1")
    @ExcelProperty("[套餐类型] 如:日卡/周卡/月卡/季卡/年卡")
    private String packageType;

    @Schema(description = "[原价]", example = "26606")
    @ExcelProperty("[原价]")
    private BigDecimal originalPrice;

    @Schema(description = "[售价]", example = "6811")
    @ExcelProperty("[售价]")
    private BigDecimal salePrice;

    @Schema(description = "[有效天数]")
    @ExcelProperty("[有效天数]")
    private Integer validDays;

    @Schema(description = "[可绑定车牌数]")
    @ExcelProperty("[可绑定车牌数]")
    private Integer bindCarLimit;

    @Schema(description = "[适用车场ID列表] JSON格式varchar，关联park_lot.id")
    @ExcelProperty("[适用车场ID列表] JSON格式varchar，关联park_lot.id")
    private String applyLotIds;

    @Schema(description = "[适用车位类型ID列表] JSON格式varchar，关联park_space_type.id")
    @ExcelProperty("[适用车位类型ID列表] JSON格式varchar，关联park_space_type.id")
    private String spaceTypeIds;

    @Schema(description = "[状态] 如:上架/下架", example = "1")
    @ExcelProperty("[状态] 如:上架/下架")
    private String status;

    @Schema(description = "[销量]", example = "13801")
    @ExcelProperty("[销量]")
    private Integer salesCount;

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
