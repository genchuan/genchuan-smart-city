package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofexperienceinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 经验信息分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClassificationOfExperienceInformationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "28173")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "所属行业")
    @ExcelProperty("所属行业")
    private String sector;

    @Schema(description = "应用场景")
    @ExcelProperty("应用场景")
    private String applicationScenarios;

    @Schema(description = "经验性质")
    @ExcelProperty("经验性质")
    private String empiricalNature;

    @Schema(description = "适用对象")
    @ExcelProperty("适用对象")
    private String applicableObjects;

    @Schema(description = "来源渠道")
    @ExcelProperty("来源渠道")
    private String sourceChannel;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}