package cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 通行稽查新增/修改 Request VO")
@Data
public class ParkTrafficInspectionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28623")
    private Long id;

    @Schema(description = "稽查记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27965")
    @NotEmpty(message = "稽查记录ID（UUID）不能为空")
    private String inspectionId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String carNumber;

    @Schema(description = "入场记录ID", example = "31444")
    private String entryId;

    @Schema(description = "离场记录ID", example = "23200")
    private String exitId;

    @Schema(description = "违规类型：套牌/逃费/无权限通行/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "违规类型：套牌/逃费/无权限通行/其他不能为空")
    private String violationType;

    @Schema(description = "违规时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "违规时间不能为空")
    private LocalDateTime violationTime;

    @Schema(description = "证据ID列表")
    private String evidenceIds;

    @Schema(description = "处置状态：未处置/处置中/已处置", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "处置状态：未处置/处置中/已处置不能为空")
    private String disposalStatus;

    @Schema(description = "处置内容")
    private String disposalContent;

    @Schema(description = "处置人ID")
    private Long disposalBy;

    @Schema(description = "处置时间")
    private LocalDateTime disposalTime;

    @Schema(description = "业务创建时间")
    private LocalDateTime inspectionCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime inspectionUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String inspectionRemark;

}