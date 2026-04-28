package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 补卡 Request VO")
@Data
public class DormCheckRecheckReqVO {

    @Schema(description = "考勤记录 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2,4")
    @NotNull(message = "考勤记录 ID 列表不能为空")
    private Long[] ids;
    @Schema(description = "补卡人", example = "1")
    private String repairUser;
    @Schema(description = "补卡时间", example = "1641022932")
    private LocalDateTime repairTime;


}