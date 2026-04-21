package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 错时规则导入 Response VO")
@Data
public class OfftimeRuleImportResp {

    @Schema(description = "导入成功条数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer successCount;

    @Schema(description = "导入失败条数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer failureCount;

    @Schema(description = "导入失败明细")
    private List<ImportFailure> failureList;

    @Data
    public static class ImportFailure {

        @Schema(description = "行号", example = "3")
        private Integer rowIndex;

        @Schema(description = "错误信息", example = "该场站下已存在相同时段的错时规则")
        private String message;
    }
}
