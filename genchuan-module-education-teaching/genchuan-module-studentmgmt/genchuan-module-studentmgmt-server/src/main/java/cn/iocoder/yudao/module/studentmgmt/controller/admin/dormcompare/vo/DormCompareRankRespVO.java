
package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 宿舍得分排名统计 response VO")
@Data
public class DormCompareRankRespVO {

    @Schema(description = "宿舍号列表，用于图表 X 轴")
    private List<String> labels;
    @Schema(description = "宿舍得分列表，用于图表 Y 轴")
    private List<BigDecimal> data;

}