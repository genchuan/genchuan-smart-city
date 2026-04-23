package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "管理后台 - 话术管理新增/修改 Request VO")
@Data
public class WordingMgmtSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "话术名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "欢迎语")
    @NotBlank(message = "话术名称不能为空")
    @Size(max = 64, message = "话术名称长度不能超过 64")
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

    @Schema(description = "状态,关联字典 wording_mgmt_status", example = "未生效",
            allowableValues = {"未生效", "已生效"})
    @InDict(type = "wording_mgmt_status")
    private String status;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
