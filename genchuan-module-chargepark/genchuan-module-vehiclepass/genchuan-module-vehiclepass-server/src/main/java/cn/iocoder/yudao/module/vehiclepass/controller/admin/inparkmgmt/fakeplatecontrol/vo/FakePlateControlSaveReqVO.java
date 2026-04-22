package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 套牌管控新增/修改 Request VO")
@Data
public class FakePlateControlSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24345")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "识别时间不能为空")
    private LocalDateTime identifyTime;

    @Schema(description = "匹配场景：同牌多停 / 车牌车型不匹配，关联字典fake_plate_control_match_scene", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "匹配场景：同牌多停 / 车牌车型不匹配，关联字典fake_plate_control_match_scene不能为空")
    private String matchScene;

    @Schema(description = "处置状态：未处理 / 处理中 / 已关闭，关联字典fake_plate_control_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "处置状态：未处理 / 处理中 / 已关闭，关联字典fake_plate_control_status不能为空")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "16619")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "处置人ID，关联system_user用户表", example = "16042")
    private Long handleUserId;

    @Schema(description = "处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置进度")
    private String handleProgress;

    @Schema(description = "忽略理由", example = "不对")
    private String ignoreReason;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}