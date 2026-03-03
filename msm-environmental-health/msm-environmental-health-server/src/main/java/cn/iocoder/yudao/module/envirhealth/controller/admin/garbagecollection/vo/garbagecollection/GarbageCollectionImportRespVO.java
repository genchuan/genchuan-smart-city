package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 收运计划导入 Response VO")
@Data
@Builder
public class GarbageCollectionImportRespVO {

    @Schema(description = "成功导入数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Integer successCount;

    @Schema(description = "失败数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer failCount;

    @Schema(description = "失败详情（行号+错误信息）")
    private List<ImportErrorDetail> errorDetails;

    @Data
    @Builder
    public static class ImportErrorDetail {
        @Schema(description = "行号（Excel中从2开始，第一行为表头）", example = "3")
        private Integer rowNum;

        @Schema(description = "错误信息", example = "区域编码不能为空")
        private String errorMsg;
    }
}