package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 话术批量保存 Request VO。前端表格内联编辑多行后点\"保存\",一次性提交所有变更。" +
        "无 id = 新增;有 id = 编辑。一批内任何一条校验失败都整体回滚,不留脏数据。")
@Data
public class WordingMgmtBatchSaveReqVO {

    @Schema(description = "本次要保存的话术列表。单次最多 200 条,防止前端误提交拖垮 DB。" +
            "列表内每一项的 id 字段:新增时留 null,编辑时传已有记录的 id",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "保存列表不能为空,至少 1 条")
    @Size(max = 200, message = "单次最多保存 200 条")
    @Valid
    private List<Item> items;

    @Schema(description = "单条话术项")
    @Data
    public static class Item {

        @Schema(description = "话术记录 ID。编辑时必传,新增时留 null", example = "1024")
        private Long id;

        @Schema(description = "话术名称,需保证全表唯一", requiredMode = Schema.RequiredMode.REQUIRED,
                example = "问候语")
        @NotBlank(message = "话术名称不能为空")
        @Size(max = 64, message = "话术名称长度不能超过 64")
        private String name;

        @Schema(description = "话术内容", requiredMode = Schema.RequiredMode.REQUIRED,
                example = "您好,请问有什么可以帮您的?")
        @NotBlank(message = "话术内容不能为空")
        private String content;

        @Schema(description = "话术类型,关联字典 wording_mgmt_type",
                requiredMode = Schema.RequiredMode.REQUIRED, example = "快捷回复",
                allowableValues = {"快捷回复", "自动回复", "投诉回复"})
        @NotBlank(message = "话术类型不能为空")
        @InDict(type = "wording_mgmt_type")
        private String type;

    }

}
