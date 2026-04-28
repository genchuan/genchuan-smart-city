package cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 各班级留宿人数统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StayMgmtStayCountRespVO {

    @Schema(description = "各班级留宿统计数据")
    private List<JSONObject> classStatistics;

}
