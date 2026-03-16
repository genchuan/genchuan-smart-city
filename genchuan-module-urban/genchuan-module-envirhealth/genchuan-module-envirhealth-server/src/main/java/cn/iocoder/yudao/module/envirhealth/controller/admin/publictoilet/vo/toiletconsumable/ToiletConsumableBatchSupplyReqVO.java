package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "环境卫生管理 - 公厕耗材批量补充登记 Request VO")
@Data
public class ToiletConsumableBatchSupplyReqVO {

    @Schema(description = "补充项列表", required = true)
    @NotEmpty(message = "补充项列表不能为空")
    private List<SupplyItem> supplyItems;

    @Data
    public static class SupplyItem {

        @Schema(description = "耗材配置ID", required = true, example = "uuid-consum-001")
        @NotNull(message = "耗材配置ID不能为空")
        private String consumableId;

        @Schema(description = "补充数量", required = true, example = "10")
        @NotNull(message = "补充数量不能为空")
        private Integer supplyQuantity;
    }
}