package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 违规等级字典新增/修改 Request VO")
@Data
public class IllegalLevelDictSaveReqVO {

    @Schema(description = "[主键ID] 违规等级唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "11586")
    private Long id;

    @Schema(description = "[违规等级编码] 唯一编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[违规等级编码] 唯一编码不能为空")
    private String levelCode;

    @Schema(description = "[违规等级名称] 如：一般/较重/严重", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "[违规等级名称] 如：一般/较重/严重不能为空")
    private String levelName;

    @Schema(description = "[排序序号] 数值越小越靠前")
    private Integer sort;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
