package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 套牌管控批量处置 Request VO")
@Data
public class FakePlateControlBatchHandleReqVO {

    @Schema(description = "记录主键ID数组", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "记录主键ID数组不能为空")
    @Size(max = 200, message = "批量操作数量不能超过200")
    private List<Long> ids;

    @Schema(description = "处置类型（核查 / 忽略）", requiredMode = Schema.RequiredMode.REQUIRED, example = "核查")
    @NotBlank(message = "处置类型不能为空")
    private String handleType;

}