package cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 经验信息录入 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ExperienceInformationInputRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9500")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "经验主题")
    @ExcelProperty("经验主题")
    private String experienceTheme;

    @Schema(description = "所属领域")
    @ExcelProperty("所属领域")
    private String isArea;

    @Schema(description = "经验提供方")
    @ExcelProperty("经验提供方")
    private String experienceProvider;

    @Schema(description = "实施时间")
    @ExcelProperty("实施时间")
    private LocalDateTime implementationTime;

    @Schema(description = "实施地点")
    @ExcelProperty("实施地点")
    private String implementationLocation;

    @Schema(description = "详细步骤")
    @ExcelProperty("详细步骤")
    private String detailSteps;

    @Schema(description = "取得成效")
    @ExcelProperty("取得成效")
    private String achieveResults;

    @Schema(description = "可借鉴要点")
    @ExcelProperty("可借鉴要点")
    private String keyPointsForReference;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}