package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 空位推送新增/修改 Request VO")
@Data
public class SpacePushSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "场站 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "场站 ID 不能为空")
    private Long stationId;

    @Schema(description = "空位信息", requiredMode = Schema.RequiredMode.REQUIRED, example = "A 区 12 号车位剩余 30 分钟")
    @NotBlank(message = "空位信息不能为空")
    private String spaceInfo;

    @Schema(description = "推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "推送状态,关联字典 space_push_status", example = "待推送",
            allowableValues = {"待推送", "已推送"})
    @InDict(type = "space_push_status")
    private String status;

    @Schema(description = "推送结果,关联字典 space_push_push_result", example = "成功",
            allowableValues = {"成功", "失败"})
    @InDict(type = "space_push_push_result")
    private String pushResult;

    @Schema(description = "反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
