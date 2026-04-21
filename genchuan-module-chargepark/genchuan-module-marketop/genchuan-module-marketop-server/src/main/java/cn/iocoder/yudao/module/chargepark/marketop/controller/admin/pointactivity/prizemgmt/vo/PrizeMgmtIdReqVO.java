package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 奖品ID Request VO")
@Data
public class PrizeMgmtIdReqVO {

    @Schema(description = "主键ID", required = true)
    private Long id;

}