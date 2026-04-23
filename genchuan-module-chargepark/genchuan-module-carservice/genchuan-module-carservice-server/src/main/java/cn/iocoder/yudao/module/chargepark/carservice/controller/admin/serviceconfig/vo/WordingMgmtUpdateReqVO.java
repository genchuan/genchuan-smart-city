package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 话术编辑 Request VO")
@Data
public class WordingMgmtUpdateReqVO {

    @Schema(description = "话术记录 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "话术记录 ID 不能为空")
    private Long id;

    @Schema(description = "话术名称,需保证唯一性", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "话术名称不能为空")
    private String name;

    @Schema(description = "话术内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "话术内容不能为空")
    private String content;

    @Schema(description = "话术类型,关联字典 wording_mgmt_type",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "快捷回复",
            allowableValues = {"快捷回复", "自动回复", "投诉回复"})
    @NotBlank(message = "话术类型不能为空")
    @InDict(type = "wording_mgmt_type")
    private String type;

}
