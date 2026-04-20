package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 宿舍考勤 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormCheckRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24531")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14725")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "考勤时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("考勤时间")
    private LocalDateTime checkTime;

    @Schema(description = "考勤状态：正常/迟到/未到", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("考勤状态：正常/迟到/未到")
    private String checkStatus;

    @Schema(description = "异常类型：无/晚归/未归", example = "1")
    @ExcelProperty("异常类型：无/晚归/未归")
    private String abnormalType;

    @Schema(description = "补卡时间")
    @ExcelProperty("补卡时间")
    private LocalDateTime repairTime;

    @Schema(description = "补卡人")
    @ExcelProperty("补卡人")
    private String repairUser;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "在寝率")
    @ExcelProperty("在寝率")
    private BigDecimal inRate;

    @Schema(description = "状态：正常/异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：正常/异常")
    private String status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}