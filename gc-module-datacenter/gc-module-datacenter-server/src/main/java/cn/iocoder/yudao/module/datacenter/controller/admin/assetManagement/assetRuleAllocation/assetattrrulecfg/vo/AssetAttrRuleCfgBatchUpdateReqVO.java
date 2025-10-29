package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class AssetAttrRuleCfgBatchUpdateReqVO {

    @Schema(description = "主键编号列表", required = true)
    @NotEmpty(message = "id 列表不能为空")
    private List<Long> ids;

    @Schema(description = "是否必选（0=可选 1=必选）", required = true)
    @NotNull(message = "是否必选不能为空")
    private Integer isRequired;
}