package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 集团信息表图表数据 Request VO")
@Data
public class GroupInfoChartReqVO {

    @Schema(description = "时间范围")
    private String timeRange;

}
