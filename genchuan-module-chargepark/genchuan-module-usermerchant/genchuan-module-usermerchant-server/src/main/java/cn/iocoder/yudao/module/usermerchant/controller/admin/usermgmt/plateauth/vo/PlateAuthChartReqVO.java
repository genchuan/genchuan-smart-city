package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 车牌认证表图表数据 Request VO")
@Data
public class PlateAuthChartReqVO {

    @Schema(description = "时间范围")
    private String timeRange;

}