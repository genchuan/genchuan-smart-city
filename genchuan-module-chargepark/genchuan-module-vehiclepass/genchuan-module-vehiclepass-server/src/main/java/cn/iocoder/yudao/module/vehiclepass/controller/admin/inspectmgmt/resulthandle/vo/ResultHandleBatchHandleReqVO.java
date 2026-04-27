package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 结果处置批量处置 Request VO")
@Data
public class ResultHandleBatchHandleReqVO {

    @Schema(description = "记录主键ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "记录主键ID数组不能为空")
    private List<Long> ids;

    @Schema(description = "处置类型：通过/执行", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "处置类型不能为空")
    private String handleType;

}