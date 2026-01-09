package cn.iocoder.yudao.module.smartcity.controller.admin.guideinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 指南信息录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GuideInformationInputRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4280")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "指南名称", example = "张三")
    @ExcelProperty("指南名称")
    private String guideName;

    @Schema(description = "适用范围")
    @ExcelProperty("适用范围")
    private String scopeOfApplication;

    @Schema(description = "发布单位")
    @ExcelProperty("发布单位")
    private String publishingUnit;

    @Schema(description = "发布日期")
    @ExcelProperty("发布日期")
    private String releaseDate;

    @Schema(description = "更新日期")
    @ExcelProperty("更新日期")
    private String updateDate;

    @Schema(description = "主要内容概述")
    @ExcelProperty("主要内容概述")
    private String mainContentOverview;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}