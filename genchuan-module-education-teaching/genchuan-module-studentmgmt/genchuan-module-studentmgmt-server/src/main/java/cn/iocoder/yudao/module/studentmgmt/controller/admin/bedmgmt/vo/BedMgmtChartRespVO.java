
package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 宿舍床位分布看板 response VO")
@Data
public class BedMgmtChartRespVO {

    @Schema(description = "总床位数")
    private Integer totalBed;
    @Schema(description = "已分配床位数")
    private Integer usedBed;
    @Schema(description = "未分配床位数")
    private Integer unusedBed;
    @Schema(description = "床位使用率")
    private BigDecimal usageRate;

    @Schema(description = "各楼栋床位统计列表")
    private List<JSONObject> buildingStats;


}