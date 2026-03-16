package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 评价对象 Dropdownlist VO")
@ExcelIgnoreUnannotated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDropdownlistVO {
    @Schema(description = "下拉框的值 (ID或Code)")
    @ExcelProperty("下拉框的值 (ID或Code)")
    private Object value; // 下拉框的值 (ID或Code)

    @Schema(description = "下拉框显示的文字")
    @ExcelProperty("下拉框显示的文字")
    private String label; // 下拉框显示的文字
}
