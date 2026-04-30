package cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 沟通管理发布 Request VO")
@Data
public class CommunicateMgmtPublishReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2,5")
    private Long[] ids;

    @Schema(description = "发布时间", example = "1776220634000")
    private LocalDateTime sendTime;

}