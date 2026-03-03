package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收运时段字典新增/修改 Request VO")
@Data
public class CollectionTimePeriodSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29897")
    private Long id;

    @Schema(description = "时段编码（如：uuid-time-period-001）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "时段编码（如：uuid-time-period-001）不能为空")
    private String periodCode;

    @Schema(description = "时段名称（如：07:30-11:30）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "时段名称（如：07:30-11:30）不能为空")
    private String periodName;

    @Schema(description = "时段开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "时段开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "时段结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "时段结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}