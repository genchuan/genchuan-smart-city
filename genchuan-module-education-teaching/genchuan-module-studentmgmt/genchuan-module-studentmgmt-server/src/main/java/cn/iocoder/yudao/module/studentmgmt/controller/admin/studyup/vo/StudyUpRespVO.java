package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 升学管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StudyUpRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20394")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1428")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "目标院校名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("目标院校名称")
    private String schoolName;

    @Schema(description = "院校类型：公办/民办", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("院校类型")
    private String schoolType;

    @Schema(description = "意向专业")
    @ExcelProperty("意向专业")
    private String major;

    @Schema(description = "升学规划内容")
    @ExcelProperty("升学规划内容")
    private String planContent;

    @Schema(description = "规划时间")
    @ExcelProperty("规划时间")
    private LocalDateTime planTime;

    @Schema(description = "跟踪记录时间")
    @ExcelProperty("跟踪记录时间")
    private LocalDateTime recordTime;

    @Schema(description = "状态：待规划/已规划", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "备注", example = "你说的对")
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
