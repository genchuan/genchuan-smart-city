package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectionfrequency.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 收运频次字典新增/修改 Request VO")
@Data
public class CollectionFrequencySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9337")
    private Long id;

    @Schema(description = "频次编码（如：uuid-frequency-001）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "频次编码（如：uuid-frequency-001）不能为空")
    private String frequencyCode;

    @Schema(description = "频次名称（如：每日/每周/每月/应急）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "频次名称（如：每日/每周/每月/应急）不能为空")
    private String frequencyName;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "备注", example = "随便")
    private String remark;

}