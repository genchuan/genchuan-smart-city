
package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 宿舍评比推送 Request VO")
@Data
public class DormCompareChartReqVO {

//    cycle (string, optional): 评比周期，可选筛选条件。
    @Schema(description = "评比周期，可选筛选条件。")
    private String cycle;


}