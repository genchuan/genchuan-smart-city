package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 奖品管理 Excel 导入 VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrizeMgmtImportExcelVO {

    @ExcelProperty("奖品名称")
    private String name;

    @ExcelProperty("奖品类型")
    private String type;

    @ExcelProperty("库存")
    private Integer stock;

    @ExcelProperty("预警阈值")
    private Integer warnThreshold;

    @ExcelProperty("绑定活动ID")
    private Long activityId;

    @ExcelProperty("奖品描述")
    private String description;

}