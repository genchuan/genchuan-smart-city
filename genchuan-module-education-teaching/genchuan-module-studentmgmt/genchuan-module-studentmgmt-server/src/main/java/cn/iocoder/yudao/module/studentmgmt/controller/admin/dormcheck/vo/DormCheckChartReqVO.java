package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "管理后台 - 宿舍考勤预警看板 Request VO")
@Data
public class DormCheckChartReqVO {

    @Schema(description = "考勤时间", example = "1641022932")
    private LocalDate checkTime;


}