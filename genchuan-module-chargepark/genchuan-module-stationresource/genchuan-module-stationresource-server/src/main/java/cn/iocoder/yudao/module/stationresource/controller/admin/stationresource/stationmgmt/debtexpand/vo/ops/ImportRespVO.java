package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 片区信息导入 Response VO")
@Data
public class ImportRespVO {

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

        @Schema(description = "错误信息", example = "片区编号已存在")
        private String message;

    }
}
