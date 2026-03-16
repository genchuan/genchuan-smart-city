package cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitorInstanceImportVO {

    @ExcelProperty("部件名称")
    private String name;

    @ExcelProperty("18位标识码")
    private String uniqueCode;

    @ExcelProperty("所属分类")
    private String categoryName;

    @ExcelProperty("所在网格")
    private String gridName;

    @ExcelProperty("坐标信息")
    private String coordinate;

    @ExcelProperty("运行状态")
    private String runStatus;

    @ExcelProperty("安装时间")
    private LocalDateTime installTime;

    @ExcelProperty("校准周期")
    private Integer calibrateCycle;

    @ExcelProperty("创建人")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("行政区划归属")
    private String areaName;

    @ExcelProperty("关联管理部件")
    private String relatedPartName;

    @ExcelProperty("下次校准时间")
    private LocalDateTime nextCalibrateTime;
}