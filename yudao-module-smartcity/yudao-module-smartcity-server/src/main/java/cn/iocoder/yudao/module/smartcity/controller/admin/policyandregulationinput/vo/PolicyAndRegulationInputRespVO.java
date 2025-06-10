package cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 政策法规录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PolicyAndRegulationInputRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15549")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "制定机关")
    @ExcelProperty("制定机关")
    private String developingAgencies;

    @Schema(description = "政策法规名称")
    @ExcelProperty("政策法规名称")
    private String nameOfPolicyAndRegulation;

    @Schema(description = "文号")
    @ExcelProperty("文号")
    private String documentNumber;

    @Schema(description = "发布日期")
    @ExcelProperty("发布日期")
    private LocalDateTime releaseDate;

    @Schema(description = "生效日期")
    @ExcelProperty("生效日期")
    private LocalDateTime effectiveDate;

    @Schema(description = "失效日期")
    @ExcelProperty("失效日期")
    private String expiringDate;

    @Schema(description = "法规类别")
    @ExcelProperty("法规类别")
    private String regulatoryCategory;

    @Schema(description = "所属领域")
    @ExcelProperty("所属领域")
    private String isArea;

    @Schema(description = "适用范围")
    @ExcelProperty("适用范围")
    private String scopeOfApplication;

    @Schema(description = "正文内容")
    @ExcelProperty("正文内容")
    private String mainContent;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}