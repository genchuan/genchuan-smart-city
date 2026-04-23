package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 荣誉管理推送 Request VO")
@Data
public class HonorMgmtPushReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5053")
    private Long id;
}