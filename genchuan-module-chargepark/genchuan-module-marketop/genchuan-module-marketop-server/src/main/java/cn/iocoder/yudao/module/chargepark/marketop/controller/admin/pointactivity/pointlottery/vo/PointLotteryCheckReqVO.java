package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 积分抽奖核查 Request VO")
@Data
public class PointLotteryCheckReqVO {

    @Schema(description = "主键ID", required = true)
    @NotNull(message = "ID不能为空")
    private Long id;

    @Schema(description = "核查结果", required = true)
    @NotBlank(message = "核查结果不能为空")
    private String checkResult;

}