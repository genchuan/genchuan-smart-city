package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "环境卫生管理模块 - 收运量对比柱状图 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionVolumeBarVO {

    @Schema(description = "时间维度(日/周/月)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-01")
    private String timeDimension;

    @Schema(description = "收运量(吨)", requiredMode = Schema.RequiredMode.REQUIRED, example = "156.5")
    private BigDecimal collectedVolume;
}