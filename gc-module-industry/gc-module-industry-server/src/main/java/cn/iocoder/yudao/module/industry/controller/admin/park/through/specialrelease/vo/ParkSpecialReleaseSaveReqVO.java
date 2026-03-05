package cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 特殊放行新增/修改 Request VO")
@Data
public class ParkSpecialReleaseSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10994")
    private Long id;

    @Schema(description = "特殊放行ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23222")
    @NotEmpty(message = "特殊放行ID（UUID）不能为空")
    private String releaseId;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String carNumber;

    @Schema(description = "放行类型：紧急开闸/特殊车辆/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "放行类型：紧急开闸/特殊车辆/其他不能为空")
    private String releaseType;

    @Schema(description = "放行原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "不对")
    @NotEmpty(message = "放行原因不能为空")
    private String reason;

    @Schema(description = "出入口ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8386")
    @NotEmpty(message = "出入口ID不能为空")
    private String entryExitId;

    @Schema(description = "放行操作人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "放行操作人ID不能为空")
    private Long releaseBy;

    @Schema(description = "放行时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "放行时间不能为空")
    private LocalDateTime releaseTime;

    @Schema(description = "核验状态：已核验/未核验", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "核验状态：已核验/未核验不能为空")
    private String verifyStatus;

    @Schema(description = "核验人ID")
    private Long verifyBy;

    @Schema(description = "核验时间")
    private LocalDateTime verifyTime;

    @Schema(description = "业务创建时间")
    private LocalDateTime releaseCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime releaseUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String releaseRemark;

}