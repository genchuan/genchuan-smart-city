package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "管理后台 - 窨井盖设施新增/修改 Request VO")
@Data
public class ManholeCoverSaveReqVO {

    @Schema(description = "自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "21008")
    private Long id;

    @Schema(description = "井盖编号（唯一）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "井盖编号（唯一）不能为空")
    private String coverNo;

    @Schema(description = "关联道路设施表road_facility的road_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13196")
    @NotNull(message = "关联道路设施表road_facility的road_id不能为空")
    private Long roadId;

    @Schema(description = "井盖类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "井盖类型不能为空")
    private String coverType;

    @Schema(description = "井盖规格（文本）")
    private String specification;

    @Schema(description = "安装时间")
    private LocalDate installTime;

    @Schema(description = "关联区域表sys_area的area_code")
    private String areaCode;

    @Schema(description = "使用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "使用状态不能为空")
    private String status;

}