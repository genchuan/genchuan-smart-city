package cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 新生报到进度看板 Request VO")
@Data
public class CheckInChartIndexRespVO {
    //totalRegisterCount (integer): 总报名人数。
    //totalConfirmCount (integer): 已确认报到人数。
    //checkinRate (decimal): 报到率。
    //accountCreatedCount (integer): 已创建账号人数。
    //accountCreateRate (decimal): 账号创建完成率。
    @Schema(description = "总报名人数")
    private Integer totalRegisterCount;
    @Schema(description = "已确认报到人数")
    private Integer totalConfirmCount;
    @Schema(description = "报到率")
    private BigDecimal checkinRate;
    @Schema(description = "已创建账号人数")
    private Integer accountCreatedCount;
    @Schema(description = "账号创建完成率")
    private BigDecimal accountCreateRate;

}