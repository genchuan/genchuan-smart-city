package cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 就诊类型 / 年级分布统计 Response VO")
@Data
public class TreatMgmtDistributionRespVO {
//    treatTypeDistribution (array): 就诊类型分布数据，包含类型名称及对应就诊数。
//    gradeDistribution (array): 年级就诊分布数据，包含年级名称及对应就诊数。

    @Schema(description = "就诊类型分布数据")
    private List<JSONObject> treatTypeDistribution;
    @Schema(description = "年级就诊分布数据")
    private List<JSONObject> gradeDistribution;


}