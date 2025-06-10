package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 指南信息分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClassificationOfGuideInformationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14005")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "适用领域")
    @ExcelProperty("适用领域")
    private String applications;

    @Schema(description = "应用场景")
    @ExcelProperty("应用场景")
    private String applicationScenarios;

    @Schema(description = "受众群体")
    @ExcelProperty("受众群体")
    private String targetAudience;

    @Schema(description = "指南性质")
    @ExcelProperty("指南性质")
    private String natureOfTheGuide;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}