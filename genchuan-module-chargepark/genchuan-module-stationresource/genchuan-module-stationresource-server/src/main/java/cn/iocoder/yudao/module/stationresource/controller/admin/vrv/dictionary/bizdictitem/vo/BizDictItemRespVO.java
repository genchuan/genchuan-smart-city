package cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 业务字典项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BizDictItemRespVO {

    @Schema(description = "[主键ID] 字典项唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "11833")
    @ExcelProperty("[主键ID] 字典项唯一标识")
    private Long id;

    @Schema(description = "[关联类型编码] 关联park_dict_type.uni_code", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[关联类型编码] 关联park_dict_type.uni_code")
    private String typeCode;

    @Schema(description = "[字典键] 如：1、0、success", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[字典键] 如：1、0、success")
    private String dictKey;

    @Schema(description = "[字典显示名] 如：男、女、成功", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[字典显示名] 如：男、女、成功")
    private String dictLabel;

    @Schema(description = "[颜色] 如：#1890ff")
    @ExcelProperty("[颜色] 如：#1890ff")
    private String color;

    @Schema(description = "[同类型内排序]")
    @ExcelProperty("[同类型内排序]")
    private Integer sort;

    @Schema(description = "[字典项描述]", example = "你猜")
    @ExcelProperty("[字典项描述]")
    private String description;

    @Schema(description = "[备注]", example = "你说的对")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[状态]如:0-禁用/1-启用", example = "2")
    @ExcelProperty("[状态]如:0-禁用/1-启用")
    private Integer status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
