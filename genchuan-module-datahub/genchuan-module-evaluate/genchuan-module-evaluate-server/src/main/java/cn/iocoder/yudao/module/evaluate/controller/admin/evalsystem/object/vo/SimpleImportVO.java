package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SimpleImportVO {
    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 1)
    private String code;
    @ExcelProperty(index = 2)
    private String areaName;
    @ExcelProperty(index = 3)
    private String objectTypeName;
    @ExcelProperty(index = 4)
    private String managerName;
    @ExcelProperty(index = 5)
    private String managerPhone;
    @ExcelProperty(index = 6)
    private String relatedName;
    @ExcelProperty(index = 7)
    private String statusId;
}