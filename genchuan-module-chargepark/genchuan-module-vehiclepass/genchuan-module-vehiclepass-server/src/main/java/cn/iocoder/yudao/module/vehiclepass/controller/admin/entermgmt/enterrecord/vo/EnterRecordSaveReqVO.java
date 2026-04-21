package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 入场记录新增/修改 Request VO")
@Data
public class EnterRecordSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28895")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/其他，关联字典enter_record_plate_color", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌颜色：蓝牌/黄牌/绿牌/其他，关联字典enter_record_plate_color不能为空")
    private String plateColor;

    @Schema(description = "车位编号")
    private String spaceNo;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime enterTime;

    @Schema(description = "记录类型：自动识别/人工补录，关联字典enter_record_record_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "记录类型：自动识别/人工补录，关联字典enter_record_record_type不能为空")
    private String recordType;

    @Schema(description = "记录状态：正常记录/异常记录，关联字典enter_record_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "记录状态：正常记录/异常记录，关联字典enter_record_status不能为空")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "20633")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "佐证图片地址")
    private String proofImage;

    @Schema(description = "修正日志标记：0-未修正 1-已修正", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "修正日志标记：0-未修正 1-已修正不能为空")
    private Boolean isCorrected;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}