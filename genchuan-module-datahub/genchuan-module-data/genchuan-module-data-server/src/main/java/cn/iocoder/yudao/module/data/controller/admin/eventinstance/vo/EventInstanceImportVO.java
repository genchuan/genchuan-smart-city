package cn.iocoder.yudao.module.data.controller.admin.eventinstance.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventInstanceImportVO {

    @ExcelProperty("事件名称")
    private String name;

    @ExcelProperty("18位标识码")
    private String uniqueCode;

    @ExcelProperty("所属分类")
    private String categoryName;

    @ExcelProperty("关联监测部件")
    private String monitorName;

    @ExcelProperty("事发坐标")
    private String coordinate;

    @ExcelProperty("事件等级")
    private String eventLevel;

    @ExcelProperty("描述信息")
    private String description;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("创建人")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("处置人")
    private String handler;

    @ExcelProperty("处置时间")
    private LocalDateTime dealTime;

    @ExcelProperty("行政区划归属")
    private String areaName;

    @ExcelProperty("关联管理事项")
    private String matterName;
}