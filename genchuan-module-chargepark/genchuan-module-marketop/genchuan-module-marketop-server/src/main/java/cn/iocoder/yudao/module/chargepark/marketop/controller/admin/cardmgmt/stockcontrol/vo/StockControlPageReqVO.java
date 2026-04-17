package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 库存管控分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StockControlPageReqVO extends PageParam {

    @Schema(description = "卡种ID")
    private Long cardId;

    @Schema(description = "库存状态")
    private String status;

    @Schema(description = "告警状态")
    private String warnStatus;

}
