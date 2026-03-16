package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 撤销原因字典新增/修改 Request VO")
@Data
public class CancelReasonDictSaveReqVO {

    @Schema(description = "[主键ID] 撤销原因记录唯一标识，自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15460")
    private Long id;

    @Schema(description = "[撤销原因编码] 撤销原因唯一编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[撤销原因编码] 撤销原因唯一编码不能为空")
    private String reasonCode;

    @Schema(description = "[撤销原因名称] 如：证据不足/违规事实认定错误/企业已整改完成/适用法规错误/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "[撤销原因名称] 如：证据不足/违规事实认定错误/企业已整改完成/适用法规错误/其他不能为空")
    private String reasonName;

    @Schema(description = "[排序序号] 排序序号，整型，默认0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[排序序号] 排序序号，整型，默认0不能为空")
    private Integer sort;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
