
package cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 最基础的数量统计 response VO")
@Data
public class ChartCountVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "数量")
    private Integer value;


}