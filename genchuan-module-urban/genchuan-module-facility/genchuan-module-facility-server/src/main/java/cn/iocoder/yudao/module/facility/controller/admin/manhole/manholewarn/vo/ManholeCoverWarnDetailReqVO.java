package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "井盖预警详情 - 请求")
public class ManholeCoverWarnDetailReqVO {

    @Schema(description = "预警主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String warnId;

}