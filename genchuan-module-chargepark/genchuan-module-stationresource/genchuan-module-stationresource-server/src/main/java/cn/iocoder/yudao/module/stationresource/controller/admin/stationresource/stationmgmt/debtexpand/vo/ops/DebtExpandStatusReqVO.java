package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Schema(description = "管理后台 - 联合追缴拓场配置新增 Request VO")
@Data
public class DebtExpandStatusReqVO {

    @Schema(description = "配置ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,2")
    @NotNull(message = "配置ID列表不能为空")
    private List<Long> ids; // 配置ID列表
}
