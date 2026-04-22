package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 兑换类目 Excel 导入 VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeCategoryImportExcelVO {

    @ExcelProperty("类目名称")
    private String name;

    @ExcelProperty("适用范围")
    private String scope;

    @ExcelProperty("排序权重")
    private Integer sort;

    @ExcelProperty("类目描述")
    private String description;

}
