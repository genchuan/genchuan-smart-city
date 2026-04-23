package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 话术新增 Request VO")
@Data
public class WordingMgmtCreateReqVO {

    @Schema(description = "话术名称,需保证唯一性", requiredMode = Schema.RequiredMode.REQUIRED, example = "问候语")
    @NotBlank(message = "话术名称不能为空")
    private String name;

    @Schema(description = "话术内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "您好,请问有什么可以帮您的?")
    @NotBlank(message = "话术内容不能为空")
    private String content;

    @Schema(description = "话术类型,关联字典 wording_mgmt_type",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "快捷回复",
            allowableValues = {"快捷回复", "自动回复", "投诉回复"})
    @NotBlank(message = "话术类型不能为空")
    @InDict(type = "wording_mgmt_type")
    private String type;

}
