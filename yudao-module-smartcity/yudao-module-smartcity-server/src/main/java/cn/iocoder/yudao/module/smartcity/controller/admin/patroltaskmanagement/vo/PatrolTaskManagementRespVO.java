package cn.iocoder.yudao.module.smartcity.controller.admin.patroltaskmanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡査任务管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PatrolTaskManagementRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22109")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "任务名称")
    @ExcelProperty("任务名称")
    private String task;

    @Schema(description = "所属计划")
    @ExcelProperty("所属计划")
    private String belongingPlan;

    @Schema(description = "巡查区域")
    @ExcelProperty("巡查区域")
    private String patrolArea;

    @Schema(description = "巡查时间")
    @ExcelProperty("巡查时间")
    private String patrolTime;

    @Schema(description = "执行人员")
    @ExcelProperty("执行人员")
    private String executive;

    @Schema(description = "预计时长")
    @ExcelProperty("预计时长")
    private String expectedDuration;

    @Schema(description = "任务描述", example = "你猜")
    @ExcelProperty("任务描述")
    private String taskDescription;

    @Schema(description = "巡查重点")
    @ExcelProperty("巡查重点")
    private String keyInspectionPoints;

    @Schema(description = "携带设备清单")
    @ExcelProperty("携带设备清单")
    private String listOfCarryingEquipment;

    @Schema(description = "完成情况说明", example = "你猜")
    @ExcelProperty("完成情况说明")
    private String completionStatusDescription;

    @Schema(description = "异常情况记录")
    @ExcelProperty("异常情况记录")
    private String abnormalSituationRecord;

    @Schema(description = "处理措施")
    @ExcelProperty("处理措施")
    private String handlingMeasures;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}