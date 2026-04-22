package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 在停状态新增/修改 Request VO")
@Data
public class InParkStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13178")
    private Long id;

    @Schema(description = "场站ID，关联场站表station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "14863")
    @NotNull(message = "场站ID，关联场站表station_info不能为空")
    private Long stationId;

    @Schema(description = "车位ID，关联车位表parking_space_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "16153")
    @NotNull(message = "车位ID，关联车位表parking_space_info不能为空")
    private Long spaceId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌号码不能为空")
    private String carNo;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime inTime;

    @Schema(description = "是否超时长：是/否，关联字典in_park_status_over_time", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否超时长：是/否，关联字典in_park_status_over_time不能为空")
    private String overTime;

    @Schema(description = "状态：正常/异常，关联字典in_park_status_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：正常/异常，关联字典in_park_status_status不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}