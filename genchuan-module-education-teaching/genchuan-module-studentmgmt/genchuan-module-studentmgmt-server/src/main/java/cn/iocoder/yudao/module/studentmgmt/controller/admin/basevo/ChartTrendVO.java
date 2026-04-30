
package cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 最基础的日期趋势数据 response VO")
@Data
public class ChartTrendVO {

    @Schema(description = "日期")
    private String date;

    @Schema(description = "数量")
    private Integer count;


}