package cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 资助系统 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FundSystemChartRespVO {

    @Schema(description = "本学期资助申请总次数")
    @ExcelProperty("本学期资助申请总次数")
    private Integer totalApplyCount;

    @Schema(description = "待审核资助申请数")
    @ExcelProperty("待审核资助申请数")
    private Integer pendingAuditCount;

    @Schema(description = "本学期申请总金额")
    @ExcelProperty("本学期申请总金额")
    private BigDecimal totalApplyAmount;

    @Schema(description = "已审核通过申请数")
    @ExcelProperty("已审核通过申请数")
    private Integer approvedCount;

    @Schema(description = "资助类型分布统计，包含类型名称、对应数量。")
    @ExcelProperty("资助类型分布统计，包含类型名称、对应数量。")
    private List<JSONObject> fundTypeDistribution;
    @Schema(description = "各年级申请趋势，包含年级名称、对应申请人数。")
    @ExcelProperty("各年级申请趋势，包含年级名称、对应申请人数。")
    private List<JSONObject> gradeApplyTrend;



}
