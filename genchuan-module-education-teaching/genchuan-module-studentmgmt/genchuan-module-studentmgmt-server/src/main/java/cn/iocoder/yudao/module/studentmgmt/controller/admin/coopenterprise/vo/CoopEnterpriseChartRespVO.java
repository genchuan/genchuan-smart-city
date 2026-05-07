package cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 校企合作 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CoopEnterpriseChartRespVO {

    @Schema(description = "总合作企业数")
    private Integer totalEnterprise;
    @Schema(description = "合作中企业数")
    private Integer cooperatingEnterprise;
    @Schema(description = "已结束合作企业数")
    private Integer finishedEnterprise;
    @Schema(description = "各系部合作企业统计")
    private List<JSONObject> deptCoopCount;
    @Schema(description = "合作趋势数据")
    private List<ChartTrendVO> coopTrend;

}

