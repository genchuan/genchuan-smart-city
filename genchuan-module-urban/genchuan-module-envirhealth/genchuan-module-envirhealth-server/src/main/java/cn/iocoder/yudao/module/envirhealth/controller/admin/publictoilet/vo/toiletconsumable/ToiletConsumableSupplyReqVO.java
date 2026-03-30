package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ToiletConsumableSupplyReqVO {

    @Schema(description = "公厕耗材配置ID（public_toilet_consumable.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "31")
    @NotNull(message = "耗材配置ID不能为空")
    private Long id;

    @Schema(description = "补充数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    @NotNull(message = "补充数量不能为空")
    @Min(value = 1, message = "补充数量必须大于0")
    private Integer supplyQuantity;

    @Schema(description = "物资照片URL列表（存入 photo_urls JSON）", example = "[\"https://xx/1.jpg\",\"https://xx/2.jpg\"]")
    private List<String> photoUrls;
}