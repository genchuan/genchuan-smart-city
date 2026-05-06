package cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 校企合作 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CoopEnterpriseDistributionRespVO {

    @Schema(description = "企业类型分布统计")
    private List<JSONObject> typeDistribution;
    @Schema(description = "系部分布统计")
    private List<ChartCountVO> deptDistribution;

}

