package cn.iocoder.yudao.module.smartcity.controller.admin.probleminput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 问题录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProblemInputRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24767")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "问题标题")
    @ExcelProperty("问题标题")
    private String questionTitle;

    @Schema(description = "问题描述", example = "你猜")
    @ExcelProperty("问题描述")
    private String problemDescription;

    @Schema(description = "提问时间")
    @ExcelProperty("提问时间")
    private LocalDateTime questionTime;

    @Schema(description = "提问人")
    @ExcelProperty("提问人")
    private String questioner;

    @Schema(description = "所属领域")
    @ExcelProperty("所属领域")
    private String isArea;

    @Schema(description = "紧急程度")
    @ExcelProperty("紧急程度")
    private String urgency;

    @Schema(description = "问题类型", example = "1")
    @ExcelProperty("问题类型")
    private String questionType;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}