package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 缴费核验放行 Request VO")
@Data
public class PayCheckReleaseReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

}