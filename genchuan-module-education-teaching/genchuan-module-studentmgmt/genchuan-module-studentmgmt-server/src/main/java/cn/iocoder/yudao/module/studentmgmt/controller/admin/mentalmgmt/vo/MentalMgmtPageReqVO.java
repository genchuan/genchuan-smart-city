package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 心理管理分页 Request VO")
@Data
public class MentalMgmtPageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "9996")
    private Long studentId;

    @Schema(description = "心理状态：正常/关注/高危", example = "2")
    private String mentalStatus;

    @Schema(description = "风险等级：低/中/高")
    private String riskLevel;

    @Schema(description = "评估时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evaluateTime;

    @Schema(description = "咨询预约时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] consultTime;

    @Schema(description = "干预时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] interveneTime;

    @Schema(description = "干预内容")
    private String interveneContent;

    @Schema(description = "状态：待评估/咨询中/已干预", example = "2")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}