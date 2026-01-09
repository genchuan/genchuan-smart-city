package cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 问题录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class QuestionClassificationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10833")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "所属领域")
    @ExcelProperty("所属领域")
    private String isArea;

    @Schema(description = "问题类型", example = "1")
    @ExcelProperty("问题类型")
    private String questionType;

    @Schema(description = "紧急程度")
    @ExcelProperty("紧急程度")
    private String urgency;

    @Schema(description = "涉及主体")
    @ExcelProperty("涉及主体")
    private String involvingTheSubject;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}