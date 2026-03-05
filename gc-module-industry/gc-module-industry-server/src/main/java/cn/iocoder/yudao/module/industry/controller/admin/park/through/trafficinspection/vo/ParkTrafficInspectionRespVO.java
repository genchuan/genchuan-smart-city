package cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 通行稽查 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkTrafficInspectionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28623")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "稽查记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27965")
    @ExcelProperty("稽查记录ID（UUID）")
    private String inspectionId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌")
    private String carNumber;

    @Schema(description = "入场记录ID", example = "31444")
    @ExcelProperty("入场记录ID")
    private String entryId;

    @Schema(description = "离场记录ID", example = "23200")
    @ExcelProperty("离场记录ID")
    private String exitId;

    @Schema(description = "违规类型：套牌/逃费/无权限通行/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("违规类型：套牌/逃费/无权限通行/其他")
    private String violationType;

    @Schema(description = "违规时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("违规时间")
    private LocalDateTime violationTime;

    @Schema(description = "证据ID列表")
    @ExcelProperty("证据ID列表")
    private String evidenceIds;

    @Schema(description = "处置状态：未处置/处置中/已处置", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("处置状态：未处置/处置中/已处置")
    private String disposalStatus;

    @Schema(description = "处置内容")
    @ExcelProperty("处置内容")
    private String disposalContent;

    @Schema(description = "处置人ID")
    @ExcelProperty("处置人ID")
    private Long disposalBy;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime disposalTime;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime inspectionCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime inspectionUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    @ExcelProperty("业务备注")
    private String inspectionRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}