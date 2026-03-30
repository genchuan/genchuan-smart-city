package cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 岗位职责划分管理新增/修改 Request VO")
@Data
public class PositionResponsibilitySaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "岗位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "岗位名称不能为空")
    private String positionName;

    @Schema(description = "岗位职责描述")
    private String responsibilityDesc;

    @Schema(description = "任职要求")
    private String qualificationReq;

    @Schema(description = "所属单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属单位不能为空")
    private String belongUnit;

    @Schema(description = "负责人")
    private String manager;

}