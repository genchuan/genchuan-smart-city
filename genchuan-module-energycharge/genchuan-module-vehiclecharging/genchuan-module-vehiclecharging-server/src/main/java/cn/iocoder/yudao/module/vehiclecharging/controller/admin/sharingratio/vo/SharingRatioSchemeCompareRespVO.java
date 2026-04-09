package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 分账方案对比响应 VO")
@Data
public class SharingRatioSchemeCompareRespVO {

    @Schema(description = "方案对比列表")
    private List<SchemeCompareItem> list;

    @Data
    public static class SchemeCompareItem {
        @Schema(description = "方案名称", example = "场站合作基础分账")
        private String name;
        @Schema(description = "分账比例（%）", example = "30.00")
        private BigDecimal ratio;
        @Schema(description = "合作方", example = "XX 能源科技")
        private String cooperator;
    }
}