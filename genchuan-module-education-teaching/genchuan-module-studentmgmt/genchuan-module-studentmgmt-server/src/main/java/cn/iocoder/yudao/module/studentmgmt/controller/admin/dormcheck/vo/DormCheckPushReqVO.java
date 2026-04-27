package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 推送 Request VO")
@Data
public class DormCheckPushReqVO {

    @Schema(description = "考勤记录 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2,4")
    @NotNull(message = "考勤记录 ID 列表不能为空")
    private Long[] ids;

    @Schema(description = "推送时间", example = "1641022932")
    private LocalDateTime pushTime;


}