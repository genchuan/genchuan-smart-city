package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 入场记录补录创建 Request VO")
@Data
public class EnterRecordCreateReqVO {

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽C54321")
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "车牌颜色（蓝牌/黄牌/绿牌/其他）", requiredMode = Schema.RequiredMode.REQUIRED, example = "黄牌")
    @NotEmpty(message = "车牌颜色不能为空")
    private String plateColor;

    @Schema(description = "车位编号", example = "A002")
    private String spaceNo;

    @Schema(description = "入场时间，支持时间戳或 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-05-15 00:00:00")
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime enterTime;

    @Schema(description = "记录类型：固定人工补录", requiredMode = Schema.RequiredMode.REQUIRED, example = "人工补录")
    @NotEmpty(message = "记录类型不能为空")
    private String recordType;

    @Schema(description = "记录状态：正常记录/异常记录", requiredMode = Schema.RequiredMode.REQUIRED, example = "正常记录")
    @NotEmpty(message = "记录状态不能为空")
    private String status;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "补录入场记录")
    private String remark;

    @Schema(description = "佐证图片地址", example = "/genchuan/chargePark/vehiclePass/enterMgmt/enterRecord/2025/04/13/789012.jpg")
    private String proofImage;
}