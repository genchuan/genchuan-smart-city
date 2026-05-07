package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 补充 Request VO")
@Data
public class CheckInSupplyReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26468")
    private Long[] ids;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8524")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "中考成绩")
    private BigDecimal examScore;

    @Schema(description = "补充信息")
    private String supplyInfo;

}