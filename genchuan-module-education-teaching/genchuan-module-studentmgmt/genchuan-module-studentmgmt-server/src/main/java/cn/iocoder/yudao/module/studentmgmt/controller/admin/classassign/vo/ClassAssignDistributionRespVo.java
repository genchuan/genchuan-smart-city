package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 班级人数 / 专业分班占比统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ClassAssignDistributionRespVo {

    @Schema(description = "各班级人数数据")
    private List<JSONObject> classStudentCount;
    @Schema(description = "各专业分班占比数据")
    private List<JSONObject> majorAssignRate;


}
