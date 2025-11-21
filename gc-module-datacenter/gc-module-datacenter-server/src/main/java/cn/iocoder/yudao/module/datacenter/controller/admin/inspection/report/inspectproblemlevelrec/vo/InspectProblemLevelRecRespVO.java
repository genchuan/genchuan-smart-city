package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查巡检问题分级记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectProblemLevelRecRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "分级ID")
    @ExcelProperty("分级ID")
    private String problemLevelId;

    @Schema(description = "关联问题ID")
    @ExcelProperty("关联问题ID")
    private String problemId;

    @Schema(description = "关联问题编码")
    @ExcelProperty("关联问题编码")
    private String problemCode;

    @Schema(description = "关联问题名称")
    @ExcelProperty("关联问题名称")
    private String problemName;

    @Schema(description = "问题级别")
    @ExcelProperty("问题级别")
    private String problemLevel;

    @Schema(description = "级别描述")
    @ExcelProperty("级别描述")
    private String levelDesc;

    @Schema(description = "处置时限")
    @ExcelProperty("处置时限")
    private Integer handleDeadline;

    @Schema(description = "分级依据")
    @ExcelProperty("分级依据")
    private String levelBasis;

    @Schema(description = "分级人")
    @ExcelProperty("分级人")
    private String levelUser;

    @Schema(description = "分级时间")
    @ExcelProperty("分级时间")
    private LocalDateTime levelTime;

    @Schema(description = "是否推送提醒")
    @ExcelProperty("是否推送提醒")
    private Boolean isPushRemind;

    @Schema(description = "提醒推送时间")
    @ExcelProperty("提醒推送时间")
    private LocalDateTime pushRemindTime;

    @Schema(description = "问题状态变更")
    @ExcelProperty("问题状态变更")
    private String problemStatusChange;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String bizCreateUser;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String bizUpdateUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}