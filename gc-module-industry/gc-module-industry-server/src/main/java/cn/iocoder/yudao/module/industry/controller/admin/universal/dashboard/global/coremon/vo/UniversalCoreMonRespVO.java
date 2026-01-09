package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremon.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 通用大屏核心要素运行监测 Response VO")
@Data
public class UniversalCoreMonRespVO {

    @Schema(description = "部件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "UUID")
    @ExcelProperty("部件ID")
    private String mngCompId;

    @Schema(description = "部件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX路电力井盖")
    @ExcelProperty("部件名称")
    private String compName;

    @Schema(description = "运行状态", example = "完好/破损")
    @ExcelProperty("运行状态")
    private String status;

    @Schema(description = "异常位置", example = "隆福社区")
    @ExcelProperty("异常位置")
    private String abnormalCoord;

    @Schema(description = "预警时间", example = "2025-12-18 10:00:00")
    @ExcelProperty("预警时间")
    private String warnTime;

    @Schema(description = "故障代码", example = "F001")
    @ExcelProperty("故障代码")
    private String faultCode;

    @Schema(description = "工单状态", example = "已处理")
    @ExcelProperty("工单状态")
    private String woStatus;

    @Schema(description = "监控ID", example = "DEV-1001")
    @ExcelProperty("监控ID")
    private String deviceId;
}
