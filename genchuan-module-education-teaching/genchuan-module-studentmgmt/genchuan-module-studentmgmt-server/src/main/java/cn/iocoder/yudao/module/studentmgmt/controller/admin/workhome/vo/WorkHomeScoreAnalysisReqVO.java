package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 班级整体发展维度评分统计 Request VO")
@Data
public class WorkHomeScoreAnalysisReqVO {

    @Schema(description = "统计周期")
    private String cycle;

    @Schema(description = "年级")
    private String grade;



}