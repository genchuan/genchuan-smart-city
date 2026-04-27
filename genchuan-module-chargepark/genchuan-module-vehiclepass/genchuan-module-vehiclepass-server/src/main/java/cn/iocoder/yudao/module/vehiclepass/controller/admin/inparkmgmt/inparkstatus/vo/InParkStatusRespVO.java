package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 在停状态 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InParkStatusRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13178")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "车位名称")
    @ExcelProperty("车位名称")
    private String spaceName;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌号码")
    private String carNo;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("入场时间")
    private LocalDateTime inTime;

    @Schema(description = "是否超时长：是/否，关联字典in_park_status_over_time", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否超时长：是/否，关联字典in_park_status_over_time")
    private String overTime;

    @Schema(description = "状态：正常/异常，关联字典in_park_status_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：正常/异常，关联字典in_park_status_status")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

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

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}