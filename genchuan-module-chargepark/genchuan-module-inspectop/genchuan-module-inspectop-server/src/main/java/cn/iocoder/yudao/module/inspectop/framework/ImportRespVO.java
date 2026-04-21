package cn.iocoder.yudao.module.inspectop.framework;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "导入响应 VO")
@Data
public class ImportRespVO {

    @Schema(description = "成功数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Integer successCount;

    @Schema(description = "失败数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer failureCount;

    @Schema(description = "失败列表")
    private List<ImportFailure> failureList;

    @Data
    public static class ImportFailure {
        @Schema(description = "行号", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
        private Integer rowIndex;

        @Schema(description = "错误信息", requiredMode = Schema.RequiredMode.REQUIRED, example = "计划名称不能为空")
        private String message;
    }
}