package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.ops;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 业务字典项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ListByTypeResp {

    // ====================== 你要的外层结构 ======================
    @Schema(description = "类型编码")
    private String typeCode;

    @Schema(description = "类型名称")
    private String typeName;

    @Schema(description = "当前类型下的字典项列表")
    private List<DictItem> itemList;

    // ====================== 内部字典项结构 ======================
    @Data
    public static class DictItem {
        @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "11833")
        private Long id;

        @Schema(description = "[字典键]")
        private String dictKey;

        @Schema(description = "[字典显示名]")
        private String dictLabel;

        @Schema(description = "[字典项描述]")
        private String description;
    }
}
