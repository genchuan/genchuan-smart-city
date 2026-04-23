package cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 电子围栏 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FenceMgmtRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "围栏名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("围栏名称")
    private String name;

    @Schema(description = "围栏区域")
    @ExcelProperty("围栏区域")
    private String area;

    @Schema(description = "关联巡检人员ID")
    @ExcelProperty("关联巡检人员ID")
    private Long userId;

    @Schema(description = "巡检人员姓名")
    @ExcelProperty("巡检人员姓名")
    private String userName;

    @Schema(description = "围栏状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("围栏状态")
    private String status;

    @Schema(description = "告警触发数")
    @ExcelProperty("告警触发数")
    private Integer alarmCount;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}