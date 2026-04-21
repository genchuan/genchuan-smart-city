package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "各班级请假次数 / 考勤异常人数统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BehaviorMgmtAttendanceCountRespVO {

    @Schema(description = "各班级统计数据，包含班级名称、请假次数、考勤异常人数")
    private List<JSONObject> classStatistics;

}
