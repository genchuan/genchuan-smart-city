package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
@Schema(description = "管理后台 - 生成欠费支付二维码 Request VO")
@Data
public class PreDiscountAutoCalculateReqVO {
    @Schema(description = "[初始总金额]", example = "4447.0")
    private BigDecimal orginalAmount;

    @Schema(description = "[欠费记录Id列表(字符串形式)]", example = "1,2,3")
    private String arrearsIdListStr;

    @Schema(description = "[车牌号]", example = "京123456") //车牌号用于判断是否白名单
    private String carNumber;

    @Schema(description = "[停车场ID]", example = "1") //车牌号用于判断是否白名单
    private Long parkLotId;
}
