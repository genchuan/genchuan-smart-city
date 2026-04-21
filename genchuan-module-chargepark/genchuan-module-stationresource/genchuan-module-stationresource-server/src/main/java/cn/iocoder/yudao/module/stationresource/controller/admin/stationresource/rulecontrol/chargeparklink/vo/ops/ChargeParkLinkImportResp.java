package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 充停联动导入 Response VO")
@Data
public class ChargeParkLinkImportResp {

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

        @Schema(description = "错误信息", example = "该场站下已存在相同优惠类型+车型的充停联动规则")
        private String message;
    }
}
