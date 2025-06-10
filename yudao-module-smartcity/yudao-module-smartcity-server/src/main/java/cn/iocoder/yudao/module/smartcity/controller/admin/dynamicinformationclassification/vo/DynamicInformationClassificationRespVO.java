package cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 动态信息分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DynamicInformationClassificationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "2221")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "信息主题")
    @ExcelProperty("信息主题")
    private String messageSubject;

    @Schema(description = "行业领域")
    @ExcelProperty("行业领域")
    private String industrySector;

    @Schema(description = "信息来源")
    @ExcelProperty("信息来源")
    private String informationSources;

    @Schema(description = "紧急程度")
    @ExcelProperty("紧急程度")
    private String urgency;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}