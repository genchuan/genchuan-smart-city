package cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 违建上报 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ReportOfIllegalConstructionsRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "32058")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "上报编号")
    @ExcelProperty("上报编号")
    private String reportNumber;

    @Schema(description = "上报人")
    @ExcelProperty("上报人")
    private String reportperson;

    @Schema(description = "上报人联系方式")
    @ExcelProperty("上报人联系方式")
    private String contactReporter;

    @Schema(description = "上报时间")
    @ExcelProperty("上报时间")
    private LocalDateTime reportTime;

    @Schema(description = "违建详细地址")
    @ExcelProperty("违建详细地址")
    private String detailedAddressConstruction;

    @Schema(description = "违建所在区域")
    @ExcelProperty("违建所在区域")
    private String constructionIsLocated;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}