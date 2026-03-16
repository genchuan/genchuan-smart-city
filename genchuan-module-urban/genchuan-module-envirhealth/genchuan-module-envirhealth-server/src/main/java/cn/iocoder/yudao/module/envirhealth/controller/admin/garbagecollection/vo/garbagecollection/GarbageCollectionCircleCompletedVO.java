package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "环境卫生管理模块 - 已完成收运计划环形图数据 Response VO")
@Data
public class GarbageCollectionCircleCompletedVO {

    @Schema(description = "名称（区域名称/品类名称）", requiredMode = Schema.RequiredMode.REQUIRED, example = "浦东新区")
    private String name;

    @Schema(description = "收运量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1250.50")
    private Double value;

    @Schema(description = "占比(%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "25.5")
    private Double proportion;
}