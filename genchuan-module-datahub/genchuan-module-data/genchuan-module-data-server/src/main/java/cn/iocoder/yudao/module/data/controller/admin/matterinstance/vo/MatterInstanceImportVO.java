package cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatterInstanceImportVO {

    @ExcelProperty("事项名称")
    private String name;

    @ExcelProperty("16位标识码")
    private String uniqueCode;

    @ExcelProperty("所属分类")
    private String categoryName; // 模板中是名称，对应 DO 的 categoryName

    @ExcelProperty("上级分类id")
    private String parentCategoryId; // 对应 DO 的 parentCategoryId

    @ExcelProperty("事发位置")
    private String location;

    @ExcelProperty("所在网格")
    private String gridName; // 模板中是名称，对应 DO 的 gridName

    @ExcelProperty("描述信息")
    private String description;

    @ExcelProperty("状态")
    private String status; // 模板中是状态名称或编码，对应 DO 的 status

    @ExcelProperty("主管部门")
    private String deptName; // 模板中是名称，对应 DO 的 deptName

    @ExcelProperty("创建人")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime; // 注意：Excel中日期需要正确解析

    @ExcelProperty("处置人")
    private String handler; // 对应 DO 的 handler

    @ExcelProperty("处置时间")
    private LocalDateTime dealTime; // 注意：Excel中日期需要正确解析

    @ExcelProperty("关联部件数")
    private Integer partCount;
}