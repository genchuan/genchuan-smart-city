package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 用户导入 Response VO")
@Data
@Builder
public class WaterSampleTestImportRespVO {

    @Schema(description = "创建成功的水样数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> createSampleNames;

    @Schema(description = "更新成功的水样数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> updateSampleNames;

    @Schema(description = "导入失败的水样集合，key 为用户名，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureSampleNames;

}
