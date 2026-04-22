package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 优惠券 Excel 导入 VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponMgmtImportExcelVO {

    @ExcelProperty("券名称")
    private String name;

    @ExcelProperty("券类型")
    private String type;

    @ExcelProperty("面额")
    private BigDecimal amount;

    @ExcelProperty("使用条件")
    private String useCondition;

    @ExcelProperty("适用场站")
    private String stationIds;

    @ExcelProperty("券描述")
    private String description;

}
