package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 升学管理分页 Request VO")
@Data
public class StudyUpPageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "1428")
    private Long studentId;

    @Schema(description = "目标院校名称", example = "赵六")
    private String schoolName;

    @Schema(description = "院校类型：公办/民办", example = "2")
    private String schoolType;

    @Schema(description = "意向专业")
    private String major;

    @Schema(description = "升学规划内容")
    private String planContent;

    @Schema(description = "规划时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] planTime;

    @Schema(description = "跟踪记录时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] recordTime;

    @Schema(description = "状态：待规划/已规划", example = "2")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}