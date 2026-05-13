package cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 升学意向 / 院校选择统计 response VO")
@Data
public class StudyUpStudyCountRespVO {

    @Schema(description = "升学意向分布统计")
    private List<ChartCountVO> intentionDistribution;
    @Schema(description = "院校类型选择分布统计")
    private List<ChartCountVO> schoolTypeDistribution;


}