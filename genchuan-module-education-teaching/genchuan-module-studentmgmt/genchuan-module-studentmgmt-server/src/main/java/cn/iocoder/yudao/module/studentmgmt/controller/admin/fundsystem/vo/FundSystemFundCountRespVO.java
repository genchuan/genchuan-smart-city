package cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 资助系统 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FundSystemFundCountRespVO {

    @Schema(description = "资助类型分布统计，包含类型名称、对应数量。")
    @ExcelProperty("资助类型分布统计，包含类型名称、对应数量。")
    private List<JSONObject> gradeStatistics;

}
