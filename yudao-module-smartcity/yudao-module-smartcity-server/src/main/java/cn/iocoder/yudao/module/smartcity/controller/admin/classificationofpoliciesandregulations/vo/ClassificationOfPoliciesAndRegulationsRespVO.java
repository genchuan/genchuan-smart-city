package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 政策法规分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClassificationOfPoliciesAndRegulationsRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4595")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "法规类别")
    @ExcelProperty("法规类别")
    private String regulatoryCategory;

    @Schema(description = "所属领域")
    @ExcelProperty("所属领域")
    private String isArea;

    @Schema(description = "主题")
    @ExcelProperty("主题")
    private String theme;

    @Schema(description = "适用对象")
    @ExcelProperty("适用对象")
    private String applicableObjects;

    @Schema(description = "效力层级")
    @ExcelProperty("效力层级")
    private String levelOfEffectiveness;

    @Schema(description = "发布部门")
    @ExcelProperty("发布部门")
    private String departmentOfPublication;

    @Schema(description = "实施时间")
    @ExcelProperty("实施时间")
    private String implementationTime;

    @Schema(description = "废止时间 ")
    @ExcelProperty("废止时间 ")
    private String revocatoryDate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}