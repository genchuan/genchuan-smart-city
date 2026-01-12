package cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 入场记录新增/修改 Request VO")
@Data
public class ParkCarEntrySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14030")
    private Long id;

    @Schema(description = "入场记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25052")
    @NotEmpty(message = "入场记录ID（UUID）不能为空")
    private String entryId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌号码不能为空")
    private String carNumber;

    @Schema(description = "车辆类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "车辆类型不能为空")
    private String carType;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime entryTime;

    @Schema(description = "入场出入口ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26377")
    @NotEmpty(message = "入场出入口ID不能为空")
    private String entryExitId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2671")
    @NotEmpty(message = "所属车场ID不能为空")
    private String lotId;

    @Schema(description = "分配车位ID", example = "18134")
    private String spaceId;

    @Schema(description = "识别设备", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "识别设备不能为空")
    private String deviceCode;

    @Schema(description = "入场类型：正常识别/无牌车/特殊放行", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "入场类型：正常识别/无牌车/特殊放行不能为空")
    private String entryType;

    @Schema(description = "预约用户ID", example = "11265")
    private Long userId;

    @Schema(description = "预约ID", example = "7069")
    private String reservationId;

    @Schema(description = "业务创建时间")
    private LocalDateTime entryCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime entryUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    private String entryRemark;

}