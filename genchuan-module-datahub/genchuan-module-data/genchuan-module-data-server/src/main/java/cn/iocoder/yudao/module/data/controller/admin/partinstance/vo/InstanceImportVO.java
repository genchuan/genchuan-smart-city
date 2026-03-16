package cn.iocoder.yudao.module.data.controller.admin.partinstance.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class InstanceImportVO {

    @ExcelProperty("部件名称")
    private String partName;

    @ExcelProperty("16位标识码")
    private String uniqueCode;

    @ExcelProperty("所属分类")
    private String categoryName;

    @ExcelProperty("所在网格")
    private String gridName;

    @ExcelProperty("坐标信息")
    private String coordinate;

    @ExcelProperty("运行状态")
    private String runStatus;

    @ExcelProperty("主管部门")
    private String deptName;

    @ExcelProperty("创建人")
    private String creator;

    @ExcelProperty("关联监测部件数")
    private Integer monitorCount;

    @ExcelProperty("行政区划归属")
    private String areaName;
}