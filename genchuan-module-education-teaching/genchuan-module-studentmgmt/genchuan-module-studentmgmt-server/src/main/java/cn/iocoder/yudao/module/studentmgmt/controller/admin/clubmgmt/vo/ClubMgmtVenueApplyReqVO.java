package cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 场馆申请 Request VO")
@Data
public class ClubMgmtVenueApplyReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long id;
    @Schema(description = "申请场馆名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "小草")
    @NotEmpty(message = "申请场馆名称不能为空")
    private String venueName;
    @Schema(description = "申请使用时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "1776220634")
    @NotEmpty(message = "申请使用时间不能为空")
    private LocalDateTime applyTime;
    @Schema(description = "申请原因", example = "小草")
    private String applyReason;




}