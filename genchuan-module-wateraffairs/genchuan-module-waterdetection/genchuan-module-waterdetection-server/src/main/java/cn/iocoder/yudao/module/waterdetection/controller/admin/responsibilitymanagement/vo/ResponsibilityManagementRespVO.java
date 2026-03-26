package cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 责任单位及责任人管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ResponsibilityManagementRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "责任类型(主体责任/监管责任/运行管理责任)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任类型(主体责任/监管责任/运行管理责任)")
    private String responsibilityType;

    @Schema(description = "责任单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任单位")
    private String responsibleUnit;

    @Schema(description = "责任人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("责任人姓名")
    private String responsiblePerson;

    @Schema(description = "职务")
    @ExcelProperty("职务")
    private String position;

    @Schema(description = "联系方式")
    @ExcelProperty("联系方式")
    private String contactInfo;

    @Schema(description = "责任范围")
    @ExcelProperty("责任范围")
    private String responsibilityScope;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}