package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 企业档案分布态势图表 Response VO")
@Data
public class EnterpriseFileChartRespVO {

    @Schema(description = "企业总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    private Integer totalCount;

    @Schema(description = "在园数", requiredMode = Schema.RequiredMode.REQUIRED, example = "105")
    private Integer inParkCount;

    @Schema(description = "退园数", requiredMode = Schema.RequiredMode.REQUIRED, example = "15")
    private Integer outParkCount;

    @Schema(description = "审核通过率", requiredMode = Schema.RequiredMode.REQUIRED, example = "0.92")
    private BigDecimal checkPassRate;

    @Schema(description = "企业类型占比数据（适配饼图）", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ChartItemVO> typeRatio;

    @Schema(description = "企业规模占比数据（适配饼图）", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ChartItemVO> scaleRatio;

    @Data
    @Schema(description = "图表项 VO")
    public static class ChartItemVO {
        @Schema(description = "名称", example = "科技")
        private String name;

        @Schema(description = "数值", example = "60")
        private Integer value;
    }
}