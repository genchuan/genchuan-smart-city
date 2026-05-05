package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 升学统计看板 response VO")
@Data
public class StudyUpChartRespVO {

    @Schema(description = "总升学意向学生数", example = "100")
    private Integer totalStudent;

    @Schema(description = "待规划学生数", example = "100")
    private Integer waitPlanStudent;

    @Schema(description = "已规划学生数", example = "100")
    private Integer plannedStudent;

    @Schema(description = "热门目标院校统计", example = "100")
    private List<JSONObject> schoolTopCount;

}