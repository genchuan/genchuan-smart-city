package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 双随机行政检查 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionObjectRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10134")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "企业名称", example = "赵六")
    @ExcelProperty("企业名称")
    private String entName;

    @Schema(description = "统一社会信用代码")
    @ExcelProperty("统一社会信用代码")
    private String creditCode;

    @Schema(description = "法定代表人")
    @ExcelProperty("法定代表人")
    private String legalPerson;

    @Schema(description = "注册地址")
    @ExcelProperty("注册地址")
    private String regAddress;

    @Schema(description = "经营范围")
    @ExcelProperty("经营范围")
    private String businessScope;

    @Schema(description = "行业类型", example = "1")
    @ExcelProperty("行业类型")
    private String industryType;

    @Schema(description = "风险等级")
    @ExcelProperty("风险等级")
    private String riskLevel;

    @Schema(description = "联系人")
    @ExcelProperty("联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}