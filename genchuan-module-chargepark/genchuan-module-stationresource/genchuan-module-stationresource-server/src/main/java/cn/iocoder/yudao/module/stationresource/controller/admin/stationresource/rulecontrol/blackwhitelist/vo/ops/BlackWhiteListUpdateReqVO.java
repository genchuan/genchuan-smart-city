package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 黑白名单更新 Request VO")
@Data
public class BlackWhiteListUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽C12345")
    @NotEmpty(message = "车牌号码不能为空")
    private String plateNo;

    @Schema(description = "名单类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "白名单")
    @NotEmpty(message = "名单类型不能为空")
    private String type;

    @Schema(description = "细分类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "业主车")
    @NotEmpty(message = "细分类型不能为空")
    private String subType;

    @Schema(description = "生效时间", example = "2025-01-01 00:00:00")
    private LocalDateTime startTime;

    @Schema(description = "失效时间", example = "2026-01-01 00:00:00")
    private LocalDateTime endTime;

    @Schema(description = "证件信息")
    private String certInfo;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
