package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宿舍考勤打卡 Request VO")
@Data
public class DormCheckCreateReqVO {

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2,3,5")
    @NotNull(message = "学生 ID不能为空")
    private Long[] studentIds;

    @Schema(description = "考勤时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "考勤时间不能为空")
    private LocalDateTime checkTime;


}