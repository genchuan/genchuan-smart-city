package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 业务字典项新增/修改 Request VO")
@Data
public class BizDictItemSaveReqVO {

    @Schema(description = "[主键ID] 字典项唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "11833")
    private Long id;

    @Schema(description = "[关联类型编码] 关联park_dict_type.uni_code", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[关联类型编码] 关联park_dict_type.uni_code不能为空")
    private String typeCode;

    @Schema(description = "[字典键] 如：1、0、success", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[字典键] 如：1、0、success不能为空")
    private String dictKey;

    @Schema(description = "[字典显示名] 如：男、女、成功", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[字典显示名] 如：男、女、成功不能为空")
    private String dictLabel;

    @Schema(description = "[颜色] 如：#1890ff")
    private String color;

    @Schema(description = "[同类型内排序]")
    private Integer sort;

    @Schema(description = "[字典项描述]", example = "你猜")
    private String description;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[状态]如:0-禁用/1-启用", example = "2")
    private Integer status;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
