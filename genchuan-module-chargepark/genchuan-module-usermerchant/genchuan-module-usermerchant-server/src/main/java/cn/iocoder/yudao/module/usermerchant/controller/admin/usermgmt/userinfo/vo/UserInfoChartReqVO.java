package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 用户信息表图表数据 Request VO")
@Data
public class UserInfoChartReqVO {

    @Schema(description = "时间范围")
    private String timeRange;

}
