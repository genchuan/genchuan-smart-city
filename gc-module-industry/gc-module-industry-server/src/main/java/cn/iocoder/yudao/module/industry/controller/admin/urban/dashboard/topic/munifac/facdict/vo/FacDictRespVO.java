package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 市政设施专题 - 设施类型字典 Response VO")
@Data
public class FacDictRespVO {

    // 字典项名称（前端用于显示）
    @Schema(description = "设施类型名称", example = "井盖")
    @ExcelProperty("设施类型名称")
    private String label;

    // 字典项值（建议与 label 保持一致）
    @Schema(description = "设施类型值", example = "井盖")
    @ExcelProperty("设施类型值")
    private String value;

}
