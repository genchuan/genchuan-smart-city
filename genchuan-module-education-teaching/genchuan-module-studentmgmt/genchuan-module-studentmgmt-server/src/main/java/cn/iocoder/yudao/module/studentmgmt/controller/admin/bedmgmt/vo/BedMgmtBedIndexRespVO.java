
package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 楼栋床位占比统计 response VO")
@Data
public class BedMgmtBedIndexRespVO {

    @Schema(description = "统计周期内分配总次数")
    private Integer assignCount;
    @Schema(description = "统计周期内调整总次数")
    private Integer adjustCount;
    @Schema(description = "今日新增分配次数")
    private Integer newAssignCount;
    @Schema(description = "今日新增调整次数")
    private Integer newAdjustCount;
    @Schema(description = "近 7 天操作趋势列表")
    private List<JSONObject> trendList;

}