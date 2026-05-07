package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分班 Request VO")
@Data
public class ClassAssignConfirmReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2,4")
    private Long[] ids;
    @Schema(description = "确认人")
    private String confirmUser;
    @Schema(description = "确认时间")
    private LocalDateTime confirmTime;

}