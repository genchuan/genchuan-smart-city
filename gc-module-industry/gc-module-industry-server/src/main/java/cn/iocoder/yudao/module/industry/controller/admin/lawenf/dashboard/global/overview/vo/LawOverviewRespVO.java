package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 执法全域数据概览 Response VO")
@Data
public class LawOverviewRespVO {

    @Schema(description = "执法案件总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @ExcelProperty("执法案件总数")
    private Integer totalCaseCount;

    @Schema(description = "执法人员总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @ExcelProperty("执法人员总数")
    private Integer totalStaffCount;

    @Schema(description = "执法设备在线数", requiredMode = Schema.RequiredMode.REQUIRED, example = "80")
    @ExcelProperty("执法设备在线数")
    private Integer onlineDeviceCount;

    @Schema(description = "今日新增案件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @ExcelProperty("今日新增案件数")
    private Integer newCaseToday;

    @Schema(description = "案件办结率 (%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "95")
    @ExcelProperty("案件办结率 (%)")
    private Double caseCompleteRate;

}
