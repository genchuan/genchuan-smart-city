package cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SceneInstanceImportVO {

    @ExcelProperty("场景名称")
    private String sceneName;

    @ExcelProperty("场景编码")
    private String sceneCode;

    @ExcelProperty("关联分类")
    private String categoryName;

    @ExcelProperty("所在网格")
    private String gridName;

    @ExcelProperty("涉及设施")
    private String facilities;

    @ExcelProperty("负责人")
    private String manager;

    @ExcelProperty("处置流程")
    private String process;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("创建人")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("关联部件数")
    private Integer partCount;

    @ExcelProperty("关联事件数")
    private Integer eventCount;

    @ExcelProperty("启用/停用时间")
    private LocalDateTime statusTime;
}