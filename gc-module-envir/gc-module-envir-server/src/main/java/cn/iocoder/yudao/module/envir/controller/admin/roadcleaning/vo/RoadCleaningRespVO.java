package cn.iocoder.yudao.module.envir.controller.admin.roadcleaning.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 道路清扫计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadCleaningRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2250")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "6462")
    @ExcelProperty("业务主键（UUID）")
    private String roadCleaningId;

    @Schema(description = "清扫计划编号")
    @ExcelProperty("清扫计划编号")
    private String planNo;

    @Schema(description = "清扫路段（关联sys_road.sys_road_id）", example = "4423")
    @ExcelProperty("清扫路段（关联sys_road.sys_road_id）")
    private String roadId;

    @Schema(description = "责任区域（关联sys_area.area_code）")
    @ExcelProperty("责任区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "清扫频次")
    @ExcelProperty("清扫频次")
    private String frequency;

    @Schema(description = "清扫时段")
    @ExcelProperty("清扫时段")
    private String timePeriod;

    @Schema(description = "负责人员（关联sys_user.id，多个用逗号分隔）")
    @ExcelProperty("负责人员（关联sys_user.id，多个用逗号分隔）")
    private String staffIds;

    @Schema(description = "清扫工具（关联sys_tool.sys_tool_id，多个用逗号分隔）")
    @ExcelProperty("清扫工具（关联sys_tool.sys_tool_id，多个用逗号分隔）")
    private String toolIds;

    @Schema(description = "计划状态（关联sys_plan_status.sys_plan_status_id）", example = "4765")
    @ExcelProperty("计划状态（关联sys_plan_status.sys_plan_status_id）")
    private String planStatusId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    @ExcelProperty("业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "质量达标率")
    @ExcelProperty("质量达标率")
    private BigDecimal qualityRate;

    @Schema(description = "问题处置数", example = "26313")
    @ExcelProperty("问题处置数")
    private Integer problemCount;

    @Schema(description = "考勤全勤率")
    @ExcelProperty("考勤全勤率")
    private BigDecimal attendanceRate;

    @Schema(description = "质量核查对比照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    @ExcelProperty("质量核查对比照片URL（多个用逗号分隔）")
    private String checkPhotoUrl;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}