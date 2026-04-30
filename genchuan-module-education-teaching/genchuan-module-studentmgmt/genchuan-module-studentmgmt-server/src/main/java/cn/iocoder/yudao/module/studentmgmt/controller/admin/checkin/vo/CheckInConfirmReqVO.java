package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 确认 Request VO")
@Data
public class CheckInConfirmReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26468")
    private Long[] ids;

}