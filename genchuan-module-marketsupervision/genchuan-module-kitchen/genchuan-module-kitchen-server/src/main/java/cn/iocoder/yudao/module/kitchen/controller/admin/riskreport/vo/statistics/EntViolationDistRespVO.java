package cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "企业违规分布（饼图2） Response VO")
public class EntViolationDistRespVO {

    @Schema(description = "违规总次数")
    private Integer totalViolationCount;

    @Schema(description = "企业违规分布列表")
    private List<Item> items;

    @Data
    @Schema(description = "企业违规分布项")
    public static class Item {
        @Schema(description = "企业名称")
        private String entName;

        @Schema(description = "违规总次数")
        private Integer violationCount;

        @Schema(description = "占比（百分比）")
        private Double percentage;
    }
}
