package cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 报修类型 / 维修完成率统计 response VO")
@Data
public class RepairMgmtCountRespVO {

    @Schema(description = "各报修类型统计数据")
    private List<JSONObject> typeStatisticsList;
    @Schema(description = "各楼栋报修统计数据")
    private List<JSONObject> buildingStatisticsList;


}