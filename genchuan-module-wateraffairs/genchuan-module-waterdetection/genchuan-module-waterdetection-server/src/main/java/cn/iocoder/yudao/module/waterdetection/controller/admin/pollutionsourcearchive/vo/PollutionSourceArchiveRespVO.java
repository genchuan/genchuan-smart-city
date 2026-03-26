package cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 周边污染源档案管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PollutionSourceArchiveRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "污染源编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("污染源编号")
    private String pollutionNo;

    @Schema(description = "污染源类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("污染源类型")
    private String pollutionType;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private Double longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private Double latitude;

    @Schema(description = "污染程度")
    @ExcelProperty("污染程度")
    private String pollutionLevel;

    @Schema(description = "治理措施")
    @ExcelProperty("治理措施")
    private String treatmentMeasures;

    @Schema(description = "治理状态")
    @ExcelProperty("治理状态")
    private String treatmentStatus;

    @Schema(description = "排查时间")
    @ExcelProperty("排查时间")
    private LocalDateTime inspectionTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}