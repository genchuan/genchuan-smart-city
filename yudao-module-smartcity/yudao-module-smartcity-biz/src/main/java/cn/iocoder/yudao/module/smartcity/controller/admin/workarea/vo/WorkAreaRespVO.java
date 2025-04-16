package cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 作业区域 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WorkAreaRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14576")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "区域编号")
    @ExcelProperty("区域编号")
    private String areaNumber;

    @Schema(description = "区域名称", example = "李四")
    @ExcelProperty("区域名称")
    private String areaName;

    @Schema(description = "区域位置")
    @ExcelProperty("区域位置")
    private String regionalLocation;

    @Schema(description = "区域面积")
    @ExcelProperty("区域面积")
    private String regionalArea;

    @Schema(description = "人员容量")
    @ExcelProperty("人员容量")
    private String personnelCapacity;

    @Schema(description = "环境风险因素")
    @ExcelProperty("环境风险因素")
    private String environmentalFactors;

    @Schema(description = "防护措施要求")
    @ExcelProperty("防护措施要求")
    private String requirementsMeasures;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}