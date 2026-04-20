package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 业务字典项新增/修改 Request VO")
@Data
public class AddReq {
    //==========================必须填写
    @Schema(description = "[关联类型编码] 关联park_dict_type.uni_code", requiredMode = Schema.RequiredMode.REQUIRED,example = "eee")
    @NotEmpty(message = "[关联类型编码] 关联park_dict_type.uni_code不能为空")
    private String typeCode;

    @Schema(description = "[字典键] 如：1、0、success", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[字典键] 如：1、0、success不能为空")
    private String dictKey;

    @Schema(description = "[字典显示名] 如：男、女、成功", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[字典显示名] 如：男、女、成功不能为空")
    private String dictLabel;

    //==========================可选但重要填写
    @Schema(description = "[字典项描述]", example = "你猜")
    private String description;

    //==========================可选但不重要填写
    //有默认蓝色#1890ff
    @Schema(description = "[颜色] 如：#1890ff")
    private String color;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    //===============无需填写=======
    //自动获取最大编号+1
    @Schema(description = "[同类型内排序]",hidden = true)
    private Integer sort;

    @Schema(description = "[状态]如:0-禁用/1-启用", example = "2",hidden = true)
    private Integer status;

}
