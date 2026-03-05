package cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 在停车辆新增/修改 Request VO")
@Data
public class ParkCarParkingSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2295")
    private Long id;

    @Schema(description = "在停记录ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "7852")
    @NotEmpty(message = "在停记录ID（UUID）不能为空")
    private String parkingId;

    @Schema(description = "入场记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8656")
    @NotEmpty(message = "入场记录ID不能为空")
    private String entryId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String carNumber;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9294")
    @NotEmpty(message = "所属车场ID不能为空")
    private String lotId;

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30221")
    @NotEmpty(message = "车位ID不能为空")
    private String spaceId;

    @Schema(description = "已停放时长（分钟）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已停放时长（分钟）不能为空")
    private Integer parkingTime;

    @Schema(description = "状态：正常/疑似套牌/异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：正常/疑似套牌/异常不能为空")
    private String parkingStatus;

    @Schema(description = "疑似套牌原因", example = "不喜欢")
    private String suspiciousReason;

    @Schema(description = "异常原因", example = "不香")
    private String abnormalReason;

    @Schema(description = "业务更新时间")
    private LocalDateTime parkingUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String parkingRemark;

}