package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExchangeCategoryExportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("类目名称")
    private String name;

    @ExcelProperty("类目描述")
    private String description;

    @ExcelProperty("商品数量")
    private Integer goodsCount;

    @ExcelProperty("类目状态")
    private String status;

    @ExcelProperty("审核人ID")
    private Long auditorId;

    @ExcelProperty("审核时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    @ExcelProperty("生效时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime effectTime;

    @ExcelProperty("排序权重")
    private Integer sort;

    @ExcelProperty("适用范围")
    private String scope;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建者名称")
    private String creatorName;

    @ExcelProperty("审核人名称")
    private String auditorName;

    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
