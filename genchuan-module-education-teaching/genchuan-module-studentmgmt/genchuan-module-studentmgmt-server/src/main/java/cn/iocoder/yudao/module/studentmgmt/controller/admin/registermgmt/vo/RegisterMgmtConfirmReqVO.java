package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报名管理确认 Request VO")
@Data
public class RegisterMgmtConfirmReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2,4")
    private Long[] ids;


    @Schema(description = "确认时间", example ="1776220634000")
    private LocalDateTime confirmTime;

}