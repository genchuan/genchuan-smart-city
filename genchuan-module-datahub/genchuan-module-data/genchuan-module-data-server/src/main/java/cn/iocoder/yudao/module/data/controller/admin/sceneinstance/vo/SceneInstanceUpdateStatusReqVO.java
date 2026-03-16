package cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Schema(description = "管理后台 - 应用场景实例批量更新状态 Request VO")
@Data
@ToString(callSuper = true)
public class SceneInstanceUpdateStatusReqVO {

    @Schema(description = "实例ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "实例ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "状态", example = "1")
    @NotNull(message = "状态不能为空")
    private String status;

    @Schema(description = "状态更新时间（时间戳，毫秒）", example = "1709452800000")
    private Long statusTime;


}