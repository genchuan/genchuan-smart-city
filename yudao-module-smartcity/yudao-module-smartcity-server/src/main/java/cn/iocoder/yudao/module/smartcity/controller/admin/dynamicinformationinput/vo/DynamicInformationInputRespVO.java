package cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 动态信息录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DynamicInformationInputRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10409")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "标题")
    @ExcelProperty("标题")
    private String title;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间")
    private LocalDateTime releaseTime;

    @Schema(description = "发布主体")
    @ExcelProperty("发布主体")
    private String publishingSubject;

    @Schema(description = "内容概述")
    @ExcelProperty("内容概述")
    private String contentOverview;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}