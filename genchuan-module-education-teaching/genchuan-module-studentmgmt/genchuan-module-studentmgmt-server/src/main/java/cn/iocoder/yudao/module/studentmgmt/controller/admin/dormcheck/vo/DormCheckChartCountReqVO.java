package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "管理后台 - 宿舍考勤预警看板 Request VO")
@Data
public class DormCheckChartCountReqVO {

    //grade (string, optional): 年级，可选筛选条件。
    //checkTime (string, optional): 考勤时间，默认今日。
    @Schema(description = "年级", example = "1")
    private String grade;
    @Schema(description = "考勤时间", example = "1641022932")
    private LocalDate checkTime;


}